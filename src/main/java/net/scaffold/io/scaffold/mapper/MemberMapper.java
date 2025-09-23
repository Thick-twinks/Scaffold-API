package net.scaffold.io.scaffold.mapper;

import net.scaffold.io.scaffold.dto.request.RegisterRequestDto;
import net.scaffold.io.scaffold.dto.response.AuthResponseDto;
import net.scaffold.io.scaffold.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class MemberMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract AuthResponseDto toAuthResponseDto(String accessToken, String refreshToken);

    public abstract AuthResponseDto.JwtTokenResponseDto toJwtTokenResponse(String token);

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    public abstract Member toMember(RegisterRequestDto dto);

    @Named("encodePassword")
    protected String encodePassword(String raw) {
        return raw == null ? null : passwordEncoder.encode(raw);
    }
}
