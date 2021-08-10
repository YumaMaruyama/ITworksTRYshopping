package com.example.demo.login.domail.model;

import lombok.Data;

@Data
public class CouponForm {
	
	private int id;
	
	private int discount;
	
	private int purchaseCountTarget;
	
	private int purchaseTotalPriceTarget;
	
	private String title;
	
	
}
