package com.example.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    PasswordEncoder passwordEncoder;
    CustomUserDetailsService customUserDetailsService;

    public UsernamePasswordAuthenticationProvider(PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = String.valueOf(authentication.getPrincipal());
        String userPw = String.valueOf(authentication.getCredentials());

        CustomUserDetails customUserDetails = (CustomUserDetails)this.customUserDetailsService.loadUserByUsername(userId);

        if (this.passwordEncoder.matches(userPw, customUserDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(customUserDetails.getUid(), null, customUserDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}