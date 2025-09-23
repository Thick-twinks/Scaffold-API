package net.scaffold.io.scaffold.mapper;

import net.scaffold.io.scaffold.dto.request.RegisterRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class MemberMapper {

    public abstract LoginResponseDto toLoginResponseDto(String accessToken, String refreshToken);

    public abstract LoginResponseDto.JwtTokenResponseDto toJwtTokenResponse(String token);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract Member toMember(RegisterRequestDto dto);
}
