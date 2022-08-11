package com.kacperKwiatkowski.tweetApp.security.role;

public enum RoleAuthorities {
    AUTHENTICATED("level:auth");

    private final String permission;

    RoleAuthorities(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
