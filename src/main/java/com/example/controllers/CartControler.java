package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.CartDto;
import com.example.models.Cart;
import com.example.services.CartService;

@RestController
public class CartControler {
	@Autowired
	CartService cartService;
	
	@PostMapping("/insertCart")
	public String insertCart(@RequestParam("productId") int productId, CartDto cartDto) {
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cartService.insertCart(cartDto, userUid, productId);
		return "test";
	}
	
	@PostMapping("/selectCart")
	public List<Cart> selectCart(){
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.cartService.selectCart(userUid);
		}
	
	@PostMapping("/deleteCart")
	public String deleteCart(@RequestBody CartDto cartDto) {
		cartService.deleteCart(cartDto);
	System.out.println(cartDto);
		return "test";
	}

}
