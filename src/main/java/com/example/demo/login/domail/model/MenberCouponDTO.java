package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class MenberCouponDTO {

	private int id;
	
	private int discount;
	
	private int purchaseCountTarget;
	
	private int purchaseTotalPriceTarget;
	
	private String title;
	
	private String couponRank;
	
	private Date registration_date;
}
