package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
	
	private String id;
	private String pw;
	private String adminName;
	private String phoneNumber;
	private String birth;
	private String address;
	private String detailAddress;
	

}
