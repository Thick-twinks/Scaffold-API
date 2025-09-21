package net.scaffold.io.scaffold.dto.response;

public record LoginResponseDto(
        JwtTokenResponseDto accessToken,
        JwtTokenResponseDto refreshToken
) {
    public record JwtTokenResponseDto(
            String token
    ) {

    }
}
