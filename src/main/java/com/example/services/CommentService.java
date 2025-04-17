package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dtos.CommentDto;
import com.example.mappers.CommentMapper;
import com.example.models.Comment;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	public void insertComment (CommentDto commentDto, int userUid, int productId) {
		Comment comment = Comment.builder()
				.userUid(userUid)
				.productId(productId)
				.comment(commentDto.getComment())
				.build();
		this.commentMapper.insertComment(comment);
	}
	
	public List<Comment> selectComment(int productId){
		return commentMapper.selectComment(productId);
	}
	
	public void updateComment(CommentDto commentDto, int reviewId, int userUid, int productId) {
		Comment comment = Comment.builder()
				.userUid(userUid)
				.productId(productId)
				.reviewId(reviewId)
				.comment(commentDto.getComment())
				.build();
		commentMapper.updateComment(comment);
	}

}
