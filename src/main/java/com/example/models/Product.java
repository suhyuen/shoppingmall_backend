package com.example.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
	private int productId;
	private int categoryId;
	private int productImageId;
	private String productName;
	private int price;
	private String size;
	private int stock;
	private ProductImages productImages;

}
