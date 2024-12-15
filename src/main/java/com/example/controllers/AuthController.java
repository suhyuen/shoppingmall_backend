package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/sendCode")
	public String sendeCode(@RequestBody String phoneNumber) {
		authService.sendVerificationCode(phoneNumber);
		return "인증번호가 전송되었습니다";
	}

	@PostMapping("/verifyCode")
	public String verifyCode(@RequestBody String phoneNumber, @RequestBody String code) {
		boolean isVerified = authService.verifyCode(phoneNumber, code);
		if (isVerified) {
			return "인증이 완료되었습니다";
		} else{
			return "인증번호가 일치하지 않거나 만료되었습니다";
		}
	}
}
