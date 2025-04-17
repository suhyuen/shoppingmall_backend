package com.example.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Order {
	private int orderId;
	private int userUid;
	private int productId;
	private int totalAmount;
	private String status;
	private LocalDateTime orderDate;
	private String deleteyn;
	private Product product;
	private ProductImages productImages;
}
