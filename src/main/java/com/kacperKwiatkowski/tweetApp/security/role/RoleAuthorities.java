package com.kacperKwiatkowski.tweetApp.security.role;

public enum RoleAuthorities {
    AUTHENTICATED("level:auth"),
    BASIC("level:basic");

    private final String permission;

    RoleAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
