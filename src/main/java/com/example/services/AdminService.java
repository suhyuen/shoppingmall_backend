package com.example.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dtos.AdminDto;
import com.example.mappers.AdminMapper;
import com.example.models.Admin;

@Service
public class AdminService {
	@Autowired
	AdminMapper adminMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void insertAdmin(AdminDto adminDto) {
		Admin admin = Admin.builder()
				.id(adminDto.getId())
				.pw(this.passwordEncoder.encode(adminDto.getPw()))
				.adminName(adminDto.getAdminName())
				.phoneNumber(adminDto.getPhoneNumber())
				.address(adminDto.getAddress())
				.detailAddress(adminDto.getDetailAddress())
				.birth(adminDto.getBirth())
				.build();
		this.adminMapper.insertAdmin(admin);
	}
	
	public void adminLogin(String id) {
		Optional<Admin> adminOptional = adminMapper.adminLogin(id);
		if(adminOptional.isPresent()) {
			System.out.println("로그인에 성공하였습니다.");
		}else {
			throw new NoSuchElementException("회원정보를 찾을 수 없습니다.");
		}
	}
	
}
