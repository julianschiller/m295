package com.baloise.m295.library.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

public class AdGroupToRoleMapper implements GrantedAuthoritiesMapper {

    private static final Set<String> RELEVANT_GROUPS = Set.of("USER", "LIBRARIAN");

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(
            Collection<? extends GrantedAuthority> authorities) {

        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(a -> a.replaceFirst("^ROLE_", ""))
                .filter(RELEVANT_GROUPS::contains)
                .map(g -> new SimpleGrantedAuthority("ROLE_" + g))
                .collect(Collectors.toSet());
    }
}