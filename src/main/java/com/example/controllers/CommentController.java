package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.CommentDto;
import com.example.models.Comment;
import com.example.services.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/insertComment")
	public String insertComment(@RequestParam("productId") int productId, @RequestBody CommentDto commentDto) {
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentService.insertComment(commentDto, userUid, productId);
		return "test";
	}
	
	@PostMapping("/selectComment")
	public List<Comment> selectComment(@RequestParam("productId") int productId){
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.commentService.selectComment(productId);
	}
	
	@PostMapping("/updateComment")
	public String updateComment(@RequestParam("productId") int productId, @RequestParam("reviewId") int reviewId, CommentDto commentDto) {
		int userUid = (Integer)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentService.updateComment(commentDto, reviewId, userUid, productId);
		return "test";
	}

}
