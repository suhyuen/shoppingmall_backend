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
        	
            if (customUserDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                // 관리자 권한인 경우 처리
                return new UsernamePasswordAuthenticationToken(customUserDetails.getUid(), null, customUserDetails.getAuthorities());
                
            } else {
                // 관리자 권한이 아닌 경우
                return new UsernamePasswordAuthenticationToken(customUserDetails.getUid(), null, customUserDetails.getAuthorities());
            }
        } else {
            // 인증 실패
            throw new BadCredentialsException("Bad Credentials");
            
        }
    }

    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}