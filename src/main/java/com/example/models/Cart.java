package com.example.models;

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
public class Cart {
	private int cartId;
	private int userUid;
	private int productId;
	private int quantity;
	private String deleteyn;
	private User user;
	private Product product;
	private ProductImages productImages;
}
