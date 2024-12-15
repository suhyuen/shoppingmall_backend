package com.example.mappers;
import com.example.dtos.UserDto;
import com.example.dtos.UserIdDto;
import com.example.models.User;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.models.User;

@Mapper
public interface UserMapper {
	
	void insertUser(User user);
	
	Optional<User> userLogin(String id);
	
	void deleteUser(User user);
	
	void updateUser(User user);
	
	UserDto selectUser(int uid);
	
	int countById( @Param("id") String id);
	
	
}
