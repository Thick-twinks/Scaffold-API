package net.scaffold.io.scaffold.entity.constant;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    REJECTED("rejected"),
    STARTED("started"),
    PENDING("pending");

    private final String code;

    ProjectStatus(String code) {
        this.code = code;
    }

}