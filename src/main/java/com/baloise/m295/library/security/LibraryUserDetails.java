package com.baloise.m295.library.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baloise.m295.library.common.User;

public class LibraryUserDetails implements UserDetails{

    private static final long serialVersionUID = 528693029808370019L;

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean active;

    public LibraryUserDetails(User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getRoles().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
            this.active = user.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return name; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isEnabled() { return active; }

}
