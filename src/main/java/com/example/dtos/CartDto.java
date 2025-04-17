package com.example.dtos;

import com.example.models.Product;
import com.example.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	private int cartId;
	private int userUid;
	private int productId;
	private int quantity;
	private String deleteyn;
}
