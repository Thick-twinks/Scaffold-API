package net.scaffold.io.scaffold.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scaffold.io.scaffold.dto.internal.MemberPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class TestController {

    @GetMapping("/get")
    @PreAuthorize("hasRole('CONTRACTOR')")
    public String get(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return "Hello " + memberPrincipal.fullName();
    }
}
