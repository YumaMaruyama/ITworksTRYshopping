package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class CancelDTO {

	private int id;
	
	private int userId;
	
	private int productId;
	
	private int purchaseId;
	
	private String content;
	
	private Date registrationDate;
	
	private Date deliveryDate;
	
	private int bankNumber;
	
	private int storeName;
	
	private int returnPoint;
	
	private int pointRepayment;
	
	private String cancelCheck;
}
