package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;
@Data
public class MenberCouponForm {

private int id;
	
	private int discount;
	
	private int purchaseCountTarget;
	
	private int purchaseTotalPriceTarget;
	
	private String title;
	
	private int couponRank;
	
	private Date registration_date;

}
