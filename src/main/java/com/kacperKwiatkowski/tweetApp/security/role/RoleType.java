package com.kacperKwiatkowski.tweetApp.security.role;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.kacperKwiatkowski.tweetApp.security.role.RoleAuthorities.AUTHENTICATED;
import static com.kacperKwiatkowski.tweetApp.security.role.RoleAuthorities.BASIC;

public enum RoleType {
    USER(Sets.newHashSet(AUTHENTICATED)),
    ADMIN(Sets.newHashSet(BASIC));

    private final Set<RoleAuthorities> permissions;

    RoleType(Set<RoleAuthorities> permissions) {
        this.permissions = permissions;
    }

    public Set<RoleAuthorities> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
