package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class DeliveryDTO {

	private int id;
	
	private String address;
	
	private String mailAddress;
	
	private String productName;
	
	private int count;
	
	private Date purchaseDate;
}
