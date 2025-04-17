package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dtos.ProductDto;
import com.example.mappers.ProductMapper;
import com.example.models.Product;

@Service
public class ProductService {
	@Autowired
	ProductMapper productMapper;
	
    public void insertProduct (ProductDto productDto) {
		Product product = Product.builder()
				.categoryId(productDto.getCategoryId())
				.productImageId(productDto.getProductImageId())
				.productName(productDto.getProductName())
				.price(productDto.getPrice())
				.size(productDto.getSize())
				.stock(productDto.getStock())
				.build();
		
		this.productMapper.insertProduct(product);
	}
    
    
    public void updateProduct (ProductDto productDto) {
    	Product product = Product.builder()
    			.productId(productDto.getProductId())
    			.productName(productDto.getProductName())
    			.size(productDto.getSize())
    			.price(productDto.getPrice())
    			.stock(productDto.getStock())
    			.build();
    	
    	this.productMapper.updateProduct(product);  		
  }

    public void deleteProduct (ProductDto productDto) {
    	Product product = Product.builder()
    			.productId(productDto.getProductId())
    			.build();
    	
    	productMapper.deleteProduct(product);
    }
    
    public List<Product> selectAllProduct(){
    	return productMapper.selectAllProduct();
    }
    
    public List<Product> selectProduct(int categoryId){
    	return productMapper.selectProduct(categoryId);
    }
    
    public Product selectDetailProduct(int productId) {
    	return productMapper.selectDetailProduct(productId);
    }
    
}
