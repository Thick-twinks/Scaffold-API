package net.scaffold.io.scaffold.dto.response;

public record AuthResponseDto(
        JwtTokenResponseDto accessToken,
        JwtTokenResponseDto refreshToken
) {
    public record JwtTokenResponseDto(
            String token
    ) {

    }
}
