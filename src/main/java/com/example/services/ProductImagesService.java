package com.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mappers.ProductImagesMapper;
import com.example.models.Product;
import com.example.models.ProductImages;

@Service
public class ProductImagesService {
	
	@Autowired
	ProductImagesMapper productImagesMapper;
	
	 // 업로드 디렉토리 설정
	private static final String UPLOAD_DIR = "/Users/suhyenlee/git/shoppingmall_backend/src/main/resources/static/images/";


	
	public ProductImages insertProductImage(MultipartFile image) throws IOException {
	    
		// 이미지 파일을 디스크에 저장하고, 저장된 파일의 경로를 생성
	    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
	    
	    //파일 저장 경로 생성
	    Path path = Paths.get(UPLOAD_DIR + fileName);
	    
	    //디렉토리가 없으면 생성
	    Files.createDirectories(path.getParent());
	    image.transferTo(path); // 이미지 저장

	    // 상대 경로 반환
	    String relativePath = "/images/" + fileName;

	    // DB에 이미지 경로 저장
	    ProductImages productImages = ProductImages.builder()
	    		.productImagePath(relativePath)
	    		.build();
	  
	    
	    productImagesMapper.insertProductImage(productImages); // DB에 저장
	    
	    System.out.println("이미지 저장 경로: " + path.toAbsolutePath());

	    return productImages;
	   
	}
	
	public Path getImagePath(String filename) {
        return Paths.get(UPLOAD_DIR + filename);
    }
	
}
