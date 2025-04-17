package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Builder
public class Admin {
	private int uid;
	private String id;
	private String pw;
	private String adminName;
	private String phoneNumber;
	private String birth;
	private String address;
	private String detailAddress;
	private String createdAt;
	private String deleteyn;
	private String auth;
}
