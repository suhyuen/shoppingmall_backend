package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.OrderDto;
import com.example.mappers.OrderMapper;
import com.example.models.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	public void insertOrder(OrderDto orderDto, int userUid, int productId) {
		Order order = Order.builder()
				.userUid(userUid)
				.productId(productId)
				.totalAmount(orderDto.getTotalAmount())
				.status(orderDto.getStatus())
				.build();
		
		this.orderMapper.insertOrder(order);
	}
	
	public List<Order> selectOrder(int userUid){
		return orderMapper.selectOrder(userUid);
	}
	
	public void deleteOrder(OrderDto orderDto) {
	    Order order = Order.builder()
	        .orderId(orderDto.getOrderId())
	        .userUid(orderDto.getUserUid()) // ★ 여기도 중요
	        .build();
	    this.orderMapper.deleteOrder(order);
	}

}
