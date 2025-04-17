package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.ProductDto;
import com.example.dtos.UserDto;
import com.example.models.Product;
import com.example.services.ProductService;

@RestController
public class ProductControllrer {
	@Autowired
	ProductService productService;
	
	@PostMapping("/insertProduct")
	public String insertProduct(@RequestBody ProductDto productDto) {
		productService.insertProduct(productDto);
		return "test";
	
	}
	
	@PostMapping("/updateProduct")
	public String updateProducrt(@RequestBody ProductDto productDto) {
		productService.updateProduct(productDto);
		return "test";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestBody ProductDto productDto) {
		productService.deleteProduct(productDto);
		return "test";
	}
	
	@PostMapping("/selectAllProduct")
	public List<Product> selectAllProduct(){
		return productService.selectAllProduct();
	}
	@PostMapping("/selectProduct")
	public List<Product> selectProduct(@RequestParam("categoryId") int categoryId){
		return productService.selectProduct(categoryId);
	}
	
	@PostMapping("/selectDetailProduct")
	public Product selectDetailProduct(@RequestParam("productId") int productId) {
		return productService.selectDetailProduct(productId);
	}
}
