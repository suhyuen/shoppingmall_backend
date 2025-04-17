package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.OrderDto;
import com.example.models.Order;
import com.example.services.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	private String insertOrder(@RequestParam("productId") int productId, @RequestBody OrderDto orderDto) {
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		orderService.insertOrder(orderDto, userUid, productId);
		return "test";
	}
	
	@PostMapping("/selectOrder")
	private List<Order> selectOrder(@RequestBody OrderDto orderDto) {
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(orderService.selectOrder(userUid));
		return orderService.selectOrder(userUid);
		
	}
	
	@PostMapping("/deleteOrder")
	private String deleteOrder(@RequestBody OrderDto orderDto) {
	    int userUid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    orderDto.setUserUid(userUid);
	    orderService.deleteOrder(orderDto);
	    return "ok";
	}

}
