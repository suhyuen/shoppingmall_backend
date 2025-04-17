package com.example.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.AdminDto;
import com.example.services.AdminService;

@RestController
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@PostMapping("/insertAdmin")
	public String insertAdmin(@RequestBody AdminDto adminDto) {
		adminService.insertAdmin(adminDto);
		return "test";
    }
	
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestBody AdminDto adminDto) {
		adminService.adminLogin(adminDto.getId());
		System.out.println("1");
		return "test";
		
	}

}
