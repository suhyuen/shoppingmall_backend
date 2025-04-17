package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.models.Order;

@Mapper
public interface OrderMapper {

	void insertOrder(Order order);
	
	List<Order> selectOrder(int userUid);
	
	void deleteOrder(Order order);
}
