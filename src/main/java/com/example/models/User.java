package com.example.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
	private int uid;
	private String id;
	private String pw;
	private String userName;
	private String birth;
	private String address;
	private String detailAddress;
	private String phoneNumber;
	private LocalDateTime createdAt;
	private String deleteyn;
	private String auth;

}
