package com.example.configs;

import com.example.authentication.AdminLoginAuthenticationFilter;
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
    	http.cors().and();
        http.formLogin((formLogin) -> formLogin.disable());
        http.httpBasic((httpBasic) -> httpBasic.disable());
        http.csrf((csrf) -> csrf.disable());
        
        http.addFilterAt(new LoginAuthenticationFilter(jwtUtil, authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // AdminLoginAuthenticationFilter는 "/adminLogin"을 처리
        http.addFilterAt(new AdminLoginAuthenticationFilter(jwtUtil, authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        // JWT 필터를 로그인 필터 앞에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), LoginAuthenticationFilter.class);
        
        http.authorizeHttpRequests(authz -> authz
        		.requestMatchers("/images/{filename}").permitAll()
        		.requestMatchers("/signup").permitAll()
        		.requestMatchers("/sendCode").permitAll()
        		.requestMatchers("/verifyCode").permitAll()
        		.requestMatchers("/check-id").permitAll()
        		.requestMatchers("/login").permitAll()
        		.requestMatchers("/insertAdmin").permitAll()
        		.requestMatchers("/adminLogin").permitAll()
        		.requestMatchers("/selectProduct").permitAll()
        		.requestMatchers("/selectAllProduct").permitAll()
        		.requestMatchers("/selectProductImage").permitAll()
        		.requestMatchers("/insertProduct").hasRole("ADMIN")
        		.requestMatchers("/updateProduct").hasRole("ADMIN")
        		.requestMatchers("/deleteProduct").hasRole("ADMIN")
        		.requestMatchers("/uploadImage").hasRole("ADMIN")
        		
        	
        		
        		
                .anyRequest().authenticated());

        return http.build();
    }
    
    
}