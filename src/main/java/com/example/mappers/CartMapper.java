package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dtos.CartDto;
import com.example.models.Cart;

@Mapper
public interface CartMapper {
	void insertCart(Cart cart);
	
	List<Cart> selectCart(int userUid);
	
	void deleteCart(Cart cart);
}
