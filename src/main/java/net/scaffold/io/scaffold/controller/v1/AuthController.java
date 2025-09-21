package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import net.scaffold.io.scaffold.dto.request.LoginRequestDto;
import net.scaffold.io.scaffold.dto.request.RefreshRequestDto;
import net.scaffold.io.scaffold.dto.response.LoginResponseDto;
import net.scaffold.io.scaffold.processor.AuthProcessor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthProcessor authProcessor;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return authProcessor.login(dto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(@RequestBody RefreshRequestDto request) {
        return ResponseEntity.ok(authProcessor.refresh(request.refreshToken()));
    }

}
