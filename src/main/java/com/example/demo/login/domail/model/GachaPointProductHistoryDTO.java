package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class GachaPointProductHistoryDTO {

	private int id;
	
	private int userId;
	
	private int pointInterchangeId;
	
	private Date purchaseDate;
	
	private String deliveryCheck;
	
	private String img;
	
	private String title;
	
	private String content;
}
