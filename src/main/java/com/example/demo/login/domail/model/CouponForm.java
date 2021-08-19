package com.example.demo.login.domail.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CouponForm {
	
	private int id;
	
	private int discount;
	
	private int purchaseCountTarget;
	
	private int purchaseTotalPriceTarget;
	
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	
	
}
