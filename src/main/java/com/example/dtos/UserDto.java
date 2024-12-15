package com.example.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private int uid;
    private String id;
    private String pw;
    private String userName;
    private String birth;
	private String address;
	private String detailAddress;
	private String phoneNumber;

}