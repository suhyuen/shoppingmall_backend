package com.example.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.models.ProductImages;

@Mapper
public interface ProductImagesMapper {
	void insertProductImage(ProductImages productImages);
}
