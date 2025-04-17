package com.example.authentication;

import com.example.mappers.AdminMapper;
import com.example.mappers.UserMapper;
import com.example.models.Admin;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;
    
    public CustomUserDetailsService(UserMapper userMapper, AdminMapper adminMapper) {
        this.userMapper = userMapper;
        this.adminMapper = adminMapper;

    }
    

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userMapper.userLogin(username);
        
        if(user.isPresent()) {
        	return new CustomUserDetails(user.get());
        }
        
        Optional<Admin> admin = adminMapper.adminLogin(username);
        
        if(admin.isPresent()) {
        	return new CustomUserDetails(admin.get());
        }
        
        throw new UsernameNotFoundException("User or Admin not found with username: " + username);

    }
    
}
