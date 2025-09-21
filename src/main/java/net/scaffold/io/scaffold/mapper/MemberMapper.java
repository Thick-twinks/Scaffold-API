package net.scaffold.io.scaffold.mapper;

import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class MemberMapper {

    public abstract LoginResponseDto toLoginResponseDto(String accessToken, String refreshToken);

    public abstract LoginResponseDto.JwtTokenResponseDto toJwtTokenResponse(String token);
}
