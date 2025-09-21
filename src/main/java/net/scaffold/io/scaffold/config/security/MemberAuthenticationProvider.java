package net.scaffold.io.scaffold.config.security;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.internal.MemberPrincipal;
import net.scaffold.io.scaffold.entity.Member;
import net.scaffold.io.scaffold.service.MemberService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();

        Member member = memberService.findMemberByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException(email);
        }

        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        var memberPrincipal = new MemberPrincipal(
                member.getId(),
                member.getEmail(),
                member.getFullName()
        );
        return new UsernamePasswordAuthenticationToken(memberPrincipal, member.getPassword(), Collections.singleton(member.getRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}