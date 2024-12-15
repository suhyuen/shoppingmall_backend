package com.example.services;

import java.time.LocalDateTime;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.mappers.AuthMapper;
import com.example.models.VerificationCode;

import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	@Autowired
	AuthMapper authMapper;
	
	@Value("${twilio.account-sid}")
	private String accountSid;
	
	@Value("${twilio.auth-token}")
	private String authToken;
	
	@Value("${twilio.phone-number}")
	private String twilioPhoneNumber;
	
	
	
	
	
	
	
	public String generateVerificationCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}
	
	public void sendVerificationCode(String phoneNumber) {
		String code = generateVerificationCode();
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setPhoneNumber(phoneNumber);
		verificationCode.setCode(code);
		verificationCode.setCode(code);
		verificationCode.setExpiresAt(LocalDateTime.now().plusMinutes(3));
		
		authMapper.deleteVerificationCode(phoneNumber);
		authMapper.insertVerificationCode(verificationCode);
	}

	public boolean verifyCode(String phoneNumber, String code) {
		VerificationCode storedCode = authMapper.getVerificationCodeByPhoneNumber(phoneNumber);
		if (storedCode == null || storedCode.getExpiresAt().isBefore(LocalDateTime.now())) {
			return false;
		}
		boolean isCodeValid = storedCode.getCode().equals(code);
        if (isCodeValid) {
            authMapper.deleteVerificationCode(phoneNumber); // 인증 완료 후 코드 삭제
        }
        return isCodeValid;
	}

}
