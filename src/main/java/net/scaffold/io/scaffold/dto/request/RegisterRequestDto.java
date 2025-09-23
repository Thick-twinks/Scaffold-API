package net.scaffold.io.scaffold.dto.request;

import net.scaffold.io.scaffold.entity.constant.MemberRole;

public record RegisterRequestDto(
        String email,
        String password,
        String fullName,
        MemberRole role
) {
}
