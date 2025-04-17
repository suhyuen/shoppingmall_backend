package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dtos.ProductDto;
import com.example.models.Product;

@Mapper
public interface ProductMapper {
	void insertProduct (Product product);
	
	void updateProduct (Product product);
	
	void deleteProduct (Product product);
	
	List<Product> selectAllProduct();
	
	List<Product> selectProduct(@Param("categoryId") int categoryId);
	
	Product selectDetailProduct(@Param("productId") int productId);
}

