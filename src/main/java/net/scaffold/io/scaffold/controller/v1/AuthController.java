package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.internal.MemberPrincipal;
import net.scaffold.io.scaffold.processor.AuthProcessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthProcessor authProcessor;

    @GetMapping("/me")
    public UUID me(@AuthenticationPrincipal MemberPrincipal userPrincipal) {
        return userPrincipal.id();
    }
}
