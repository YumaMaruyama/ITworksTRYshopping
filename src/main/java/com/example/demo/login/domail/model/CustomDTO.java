package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustomDTO {

	private int id;
	
	private int userId;
	
	private int productId;
	
	private int purchaseId;
	
	private String memory;
	
	private String hardDisc;
	
	private String cpu;
	
	private int purchasePrice;
	
	private int purchaseCheck;
	
	private Date registrationDate;
	
	private int selectCheck;
}
