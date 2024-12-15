package com.example.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.UserDto;
import com.example.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserDto userDto) {
		userService.signup((userDto));
		return "test";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserDto userDto) {
		userService.login(userDto.getId());
		return "test";
	}
	
	@PostMapping("/deleteUser")
	public void deleteUser(@RequestBody UserDto userDto) {
		int userUid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				userService.deleteUser(userDto, userUid);
	}
	
	@PostMapping("/updateUser")
	public void updateUser(@RequestBody UserDto userDto) {
		int userUid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userService.updateUser(userDto, userUid);
		
	}
	
	@PostMapping("/userinf")
	public UserDto selectUser(){
		int userUid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.selectUser(userUid);
	}
	
	@PostMapping("/check-id")
    public ResponseEntity<String> checkIdDuplicate(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        boolean isDuplicate = userService.isIdDuplicate(id);
        
        if (isDuplicate) {
            return ResponseEntity.ok("ID is already taken.");
        } else {
            return ResponseEntity.ok("ID is available.");
        }
    }

}
