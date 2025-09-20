package net.scaffold.io.scaffold.entity.constant;

public enum MemberRole {
    CONTRACTOR,
    CUSTOMER,
    INSPECTOR;

    public String getAuthority() {
        return "ROLE_" + name();
    }
}
