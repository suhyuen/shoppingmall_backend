package com.example.dtos;

import com.example.models.ProductImages;

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
public class ProductDto {
	private int productId;
	private int categoryId;
	private int productImageId;
	private String productName;
	private int price;
	private String size;
	private int stock;
}
