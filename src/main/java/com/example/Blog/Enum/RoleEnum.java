package com.example.Blog.Enum;

import lombok.Getter;

public enum RoleEnum {

    USER(1L,"USER"),

    ADMIN(2L,"ADMIN");

    @Getter
    private final Long roleId;

    @Getter
    private final String roleName;

    RoleEnum(Long roleId, String roleName){

        this.roleId = roleId;
        this.roleName = roleName;

    }
}
