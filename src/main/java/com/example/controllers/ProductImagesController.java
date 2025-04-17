package com.example.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.models.ProductImages;
import com.example.services.ProductImagesService;

@RestController
public class ProductImagesController {
    
    @Autowired
    ProductImagesService productImagesService;
    
    @PostMapping("/uploadImage")
    public ResponseEntity<ProductImages> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            ProductImages productImages = productImagesService.insertProductImage(image);
            return new ResponseEntity<>(productImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private static final String UPLOAD_DIR = "/Users/suhyenlee/git/shoppingmall_backend/src/main/resources/static/images/";
    
    @GetMapping("/images/{filename}")
    
    public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
        try {
        	System.out.println("Received request for image: " + filename);
        	Path imagePath = Paths.get(UPLOAD_DIR + filename);
            System.out.println("Image path requested: " + imagePath.toString());

            // 해당 경로에 파일이 존재하는지 확인
            if (Files.exists(imagePath)) {
                String contentType = Files.probeContentType(imagePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                Resource resource = new UrlResource(imagePath.toUri()); 
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
