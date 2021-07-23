package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewForm {

private int id;
	
	private int userId;
	
	private int productId;
	
	private String rating;
	
	private String title;
	
	private String content;
	
	private Date registrationDate;
}
