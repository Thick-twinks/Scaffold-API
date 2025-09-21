package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.processor.AuthProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthProcessor authProcessor;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return authProcessor.login(dto);
    }
}
