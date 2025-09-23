package net.scaffold.io.scaffold.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.config.security.MemberAuthenticationProvider;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.request.RefreshRequestDto;
import net.scaffold.io.scaffold.dto.request.RegisterRequestDto;
import net.scaffold.io.scaffold.dto.response.AuthResponseDto;
import net.scaffold.io.scaffold.entity.Member;
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

    public AuthResponseDto login(LoginRequestDto dto) {
        log.info("Process login: dto {}", dto);
        authValidator.prevalidateAuth(dto);
        var member = memberService.findMemberByEmail(dto.email());
        authValidator.validateAuth(member);
        memberAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );
        String accessToken = jwtService.generateToken(member.getEmail(), member.getId());
        String refreshToken = jwtService.generateRefreshToken(member.getId());
        return memberMapper.toAuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto register(RegisterRequestDto dto) {
        log.info("Process registration: dto {}", dto);
        authValidator.prevalidateRegister(dto);

        Member member = memberMapper.toMember(dto);
        memberService.save(member);

        String accessToken = jwtService.generateToken(member.getEmail(), member.getId());
        String refreshToken = jwtService.generateRefreshToken(member.getId());

        return memberMapper.toAuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto refresh(RefreshRequestDto dto) {
        log.info("Process refresh: dto {}", dto);
        String memberProfileUid = jwtService.validateRefreshToken(dto.refreshToken());
        authValidator.validateRefresh(memberProfileUid);

        UUID memberId = UUID.fromString(memberProfileUid);
        var member = memberService.findMemberById(memberId);

        authValidator.validateAuth(member);
        jwtService.revokeToken(memberProfileUid);

        String newAccessToken = jwtService.generateToken(member.getEmail(), member.getId());
        String newRefreshToken = jwtService.generateRefreshToken(member.getId());
        return memberMapper.toAuthResponseDto(newAccessToken, newRefreshToken);
    }

}
