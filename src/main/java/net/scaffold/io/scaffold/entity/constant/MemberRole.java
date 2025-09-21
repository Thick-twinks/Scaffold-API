package net.scaffold.io.scaffold.entity.constant;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
    CONTRACTOR,
    CUSTOMER,
    INSPECTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
