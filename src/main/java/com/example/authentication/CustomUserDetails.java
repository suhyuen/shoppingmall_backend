package com.example.authentication;

import com.example.models.Admin;
import com.example.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {
    private int uid;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user){
        this.uid = user.getUid();
        this.username = user.getId();
        this.password = user.getPw();
        this.authorities = List.of(() -> user.getAuth());
    }
    
    public CustomUserDetails(Admin admin) {
    	this.uid = admin.getUid();
    	this.username = admin.getId();
    	this.password = admin.getPw();
    	this.authorities = List.of(() -> admin.getAuth());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }
    

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
