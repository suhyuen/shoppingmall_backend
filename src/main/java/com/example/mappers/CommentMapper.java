package com.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.models.Comment;

@Mapper
public interface CommentMapper {
	void insertComment (Comment comment);
	
	List<Comment> selectComment(int productId);
	
	void updateComment(Comment comment);
}
