package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {

	private int id;
	
	private int userId;
	
	private int productId;
	
	private int rating;
	
	private String title;
	
	private String content;
	
	private Date registrationDate;
	
	private String userName;
	
	private String usersId;
}
