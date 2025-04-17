package com.example.mappers;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.models.Admin;

@Mapper
public interface AdminMapper {
	void insertAdmin(Admin admin);
	
	Optional<Admin> adminLogin(String id);

}
