package com.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
	private int orderId;
	private int userUid;
	private int productId;
	private int totalAmount;
	private String status;
	private String deleteyn;


}
