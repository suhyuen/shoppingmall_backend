package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.CartDto;
import com.example.mappers.CartMapper;
import com.example.models.Cart;

@Service
public class CartService {
	
	@Autowired
	CartMapper cartMapper;
	
	public void insertCart (CartDto cartDto, int userUid, int productId) {
		Cart cart = Cart.builder()
				.userUid(userUid)
				.productId(productId)
				.quantity(cartDto.getQuantity())
				.build();
		this.cartMapper.insertCart(cart);
	}
	
	public List<Cart> selectCart(int userUid){
		return cartMapper.selectCart(userUid);
	}
	
	public void deleteCart(CartDto cartDto) {
		Cart cart = Cart.builder()
				.cartId(cartDto.getCartId())
				.build();
		this.cartMapper.deleteCart(cart);
	}
}
