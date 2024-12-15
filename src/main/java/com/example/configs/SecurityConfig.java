package com.example.configs;

import com.example.authentication.JwtAuthenticationFilter;
import com.example.authentication.LoginAuthenticationFilter;
import com.example.authentication.UsernamePasswordAuthenticationProvider;
import com.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Security;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;

    public SecurityConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider){
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(new AuthenticationProvider[]{
                this.usernamePasswordAuthenticationProvider});
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http)throws Exception {
    	http.cors();
        http.formLogin((formLogin) -> formLogin.disable());
        http.httpBasic((httpBasic) -> httpBasic.disable());
        http.csrf((csrf) -> csrf.disable());
        http.addFilterAt(new LoginAuthenticationFilter(jwtUtil, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), LoginAuthenticationFilter.class);

        http.authorizeHttpRequests(authz -> authz
        		.requestMatchers("/signup").permitAll()
        		.requestMatchers("/sendCode").permitAll()
        		.requestMatchers("/verifyCode").permitAll()
        		.requestMatchers("/check-id").permitAll()
        		.requestMatchers("/login").permitAll()
        		
        		
                .anyRequest().authenticated());

        return http.build();
    }
    
    
}