package com.baloise.m295.library.security;

import org.springframework.security.crypto.password.PasswordEncoder;

@Deprecated
public class PasswordEncoderDummy implements PasswordEncoder {

    /** {@inheritDoc} */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /** {@inheritDoc} */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

}
