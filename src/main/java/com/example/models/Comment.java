package com.example.models;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cglib.core.Local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
	private int reviewId;
	private int userUid;
	private int productId;
	private String comment;
	private LocalDateTime reviewDate;
	private String reviewDeleteyn;
	private User user;
}
