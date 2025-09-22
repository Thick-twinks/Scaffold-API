package net.scaffold.io.scaffold.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.config.security.MemberAuthenticationProvider;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.mapper.MemberMapper;
import net.scaffold.io.scaffold.service.JwtService;
import net.scaffold.io.scaffold.service.MemberService;
import net.scaffold.io.scaffold.validator.AuthValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthProcessor {
    private final MemberService memberService;
    private final AuthValidator authValidator;
    private final MemberMapper memberMapper;
    private final JwtService jwtService;
    private final MemberAuthenticationProvider memberAuthenticationProvider;

    public LoginResponseDto login(LoginRequestDto dto) {
        log.info("Process login: dto {}", dto);
        authValidator.prevalidateLogin(dto);
        var member = memberService.findMemberByEmail(dto.email());
        authValidator.validateLogin(member);
        memberAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );
        String accessToken = jwtService.generateToken(member.getEmail(), member.getId());
        String refreshToken = jwtService.generateRefreshToken(member.getId());
        return memberMapper.toLoginResponseDto(accessToken, refreshToken);
    }
}
