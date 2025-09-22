package net.scaffold.io.scaffold.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.config.security.MemberAuthenticationProvider;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.request.RegisterRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.entity.Member;
import net.scaffold.io.scaffold.mapper.MemberMapper;
import net.scaffold.io.scaffold.service.JwtService;
import net.scaffold.io.scaffold.service.MemberService;
import net.scaffold.io.scaffold.validator.AuthValidator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

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

    public LoginResponseDto register(RegisterRequestDto dto) {
        log.info("Process registration: dto {}", dto);

        authValidator.prevalidateRegister(dto);

        if (memberService.findMemberByEmail(dto.email()) != null) {
            throw new RuntimeException("USER_ALREADY_EXISTS");
        }

        // Создание сущности
        Member newMember = new Member();
        newMember.setEmail(dto.email());
        newMember.setPassword(passwordEncoder.encode(dto.password()));
        newMember.setFullName(dto.fullName());
        newMember.setRole(dto.role());

        Member savedMember = memberService.createMember(newMember);

        // Генерация токенов
        String accessToken = jwtService.generateToken(savedMember.getEmail(), savedMember.getId());
        String refreshToken = jwtService.generateRefreshToken(savedMember.getId());

        return memberMapper.toLoginResponseDto(accessToken, refreshToken);
    }
}
