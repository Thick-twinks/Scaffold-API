package net.scaffold.io.scaffold.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtService {
    private final Key key;
    private final long jwtExpirationMs;
    private final StringRedisTemplate redisTemplate;
    private final Duration refreshTokenTtl;

    public JwtService(@Value("${jwt.secret:}") String secret,
                      @Value("${jwt.expiration-ms:3600000}") long jwtExpirationMs,
                      StringRedisTemplate redisTemplate,
                      @Value("${jwt.refresh-token-duration-ms:2592000000}") long refreshTokenDurationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = jwtExpirationMs;
        this.redisTemplate = redisTemplate;
        this.refreshTokenTtl = Duration.ofMillis(refreshTokenDurationMs);
    }

    public String generateRefreshToken(UUID memberUid) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, memberUid.toString(), refreshTokenTtl);
        return token;
    }

    public String validateRefreshToken(String token) {
        return redisTemplate.opsForValue().get(token); // null если не найден или истёк
    }

    public void revokeToken(String token) {
        redisTemplate.delete(token);
    }

    public String generateToken(String email, UUID id) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id.toString())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public UUID extractId(String token) {
        String id = extractAllClaims(token).get("id", String.class);
        return id == null ? null : UUID.fromString(id);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            return Jwts.claims();
        }
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration == null || expiration.before(new Date());
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractEmail(token);
        return (tokenUsername != null && tokenUsername.equals(username) && !isTokenExpired(token));
    }
}
