package com.payam.learn.usermanagement.security;

import com.payam.learn.usermanagement.models.User;
import org.springframework.security.core.GrantedAuthority;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

public class JwtUser implements UserDetails {

    private String email;
    private String password;
    private UUID id;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(User user) {
        id = user.getId();
        email = user.getEmail();
        password = user.getPassword();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
