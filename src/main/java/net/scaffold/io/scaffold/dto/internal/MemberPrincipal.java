package net.scaffold.io.scaffold.dto.internal;

import java.util.UUID;

public record MemberPrincipal(
        UUID id,
        String email,
        String fullName
) {
}
