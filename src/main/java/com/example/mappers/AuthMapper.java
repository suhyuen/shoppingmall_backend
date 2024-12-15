package com.example.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.AssertingParty.Verification;

import com.example.models.VerificationCode;

@Mapper
public interface AuthMapper {
	void insertVerificationCode(VerificationCode verificationCode);

	VerificationCode getVerificationCodeByPhoneNumber(String phoneNumber);
	
	void deleteVerificationCode(String phoneNumber);
}
