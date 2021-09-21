package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseDTO {

	private int id;

	private int purchaseId;

	private int user_id;

	private int product_id;

	private int product_count;

	private Date purchase_date;

	private int purchaseCreditId;

	private String menberCouponCheck;

	private int custom_id;

	private int pcDataId;

	private String pcName;

	private int price;

	private String pcImg;

	private String memory;

	private String hardDisc;

	private String cpu;

	private int customPrice;

	private int totalPrice;

	private int purchaseCheck;

	private String cancelCheck;

	private int couponId;

	private String cancelResult;

	private int point;

	private int pointAdd;

	private int pointUse;

	private int pointRepayment;

	private boolean pointUseCheck;

}
