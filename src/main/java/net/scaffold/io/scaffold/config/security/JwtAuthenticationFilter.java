package net.scaffold.io.scaffold.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.internal.MemberPrincipal;
import net.scaffold.io.scaffold.service.JwtService;
import net.scaffold.io.scaffold.service.MemberService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final MemberAuthenticationProvider memberAuthenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                email = jwtService.extractEmail(token);
            } catch (Exception ignored) {
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var member = memberService.findMemberByEmail(email);
            if (member != null && jwtService.validateToken(token, member.getEmail())) {
                var memberPrincipal = new MemberPrincipal(
                        member.getId(),
                        member.getEmail(),
                        member.getFullName()
                );
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        memberPrincipal,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(member.getRole().getAuthority()))
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}