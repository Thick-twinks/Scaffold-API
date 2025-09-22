package net.scaffold.io.scaffold.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.config.security.MemberAuthenticationProvider;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.request.RefreshRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.mapper.MemberMapper;
import net.scaffold.io.scaffold.service.JwtService;
import net.scaffold.io.scaffold.service.MemberService;
import net.scaffold.io.scaffold.validator.AuthValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public LoginResponseDto refresh(RefreshRequestDto dto) {
        log.info("Process refresh: refreshToken {}", dto);
        String memberProfileUid = jwtService.validateRefreshToken(dto.refreshToken());
        authValidator.validateId(memberProfileUid);
        UUID memberId = UUID.fromString(memberProfileUid);
        var member = memberService.findMemberById(memberId);
        authValidator.validateLogin(member);
        jwtService.revokeToken(memberProfileUid);
        String newAccessToken = jwtService.generateToken(member.getEmail(), member.getId());
        String newRefreshToken = jwtService.generateRefreshToken(member.getId());
        return memberMapper.toLoginResponseDto(newAccessToken, newRefreshToken);
    }

}
