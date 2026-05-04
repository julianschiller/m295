package com.baloise.m295.library.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

public class AdGroupToRoleMapper implements GrantedAuthoritiesMapper {

    private static final Set<String> RELEVANT_GROUPS = Set.of("ROLE_USER", "ROLE_LIBRARIAN");

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(
            Collection<? extends GrantedAuthority> authorities) {

        return authorities.stream()
            .filter(a -> RELEVANT_GROUPS.contains(a.getAuthority()))
            .collect(Collectors.toSet());
        
    }
}