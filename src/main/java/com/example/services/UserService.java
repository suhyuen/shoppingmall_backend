package com.example.services;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.dtos.UserDto;
import com.example.mappers.UserMapper;
import com.example.models.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void signup(UserDto userDto) {
		User user = User.builder()
				.id(userDto.getId())
				.pw(this.passwordEncoder.encode(userDto.getPw()))
				.userName(userDto.getUserName())
				.birth(userDto.getBirth())
				.phoneNumber(userDto.getPhoneNumber())
				.address(userDto.getAddress())
				.detailAddress(userDto.getDetailAddress())
				.build();
		this.userMapper.insertUser(user);
	}
	
	public void login(String id) {
		Optional<User> userOptional = userMapper.userLogin(id);
		if(userOptional.isPresent()) {
			System.out.println("로그인에 성공하였습니다.");
		} else {
			throw new NoSuchElementException("회원정보를 찾을 수 없습니다");
		}
	}
	
	public void deleteUser(UserDto userDto, int uid) {
		User user = User.builder()
				.uid(uid)
				.build();
		
		userMapper.deleteUser(user);
	}
	
	public void updateUser(UserDto userDto, int uid) {
		User user = User.builder()
				.uid(uid)
				.userName(userDto.getUserName())
				.birth(userDto.getBirth())
				.address(userDto.getAddress())
				.detailAddress(userDto.getDetailAddress())
				.build();
		
		userMapper.updateUser(user);
	}
	
	public UserDto selectUser(int userUid) {
		return userMapper.selectUser(userUid);
	}
	
	public boolean isIdDuplicate(String id) {
		int count = userMapper.countById(id);
		return count >0;
	}

}
