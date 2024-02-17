package com.sandeep.securityone.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

import static com.sandeep.securityone.entities.Permission.*;

@RequiredArgsConstructor

public enum Role {
    USER(
            Set.of(
                    USER_READ,
                    USER_CREATE,
                    USER_UPDATE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,

                    USER_READ,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_DELETE,

                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;

    }
}
