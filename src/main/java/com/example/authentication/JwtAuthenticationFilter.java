package com.example.authentication;

import com.example.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if(jwt == null || !jwt.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }


        if(jwtUtil.isExpired(jwt)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // role 과 userUid 를 SecurityContextHolder에 넣어줌
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(jwtUtil.getRole(jwt));
        Authentication authentication = new UsernamePasswordAuthenticationToken(jwtUtil.getUserUid(jwt), null, List.of(grantedAuthority));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
