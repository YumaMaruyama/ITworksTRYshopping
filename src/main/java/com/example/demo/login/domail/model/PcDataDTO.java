package com.example.demo.login.domail.model;

import lombok.Data;

@Data
public class PcDataDTO {

	private int id;

	private String company;

	private String os;

	private String pc_name;

	private int pc_size;

	private int price;

	private int totalPrice;

	private String detail;

	private int product_stock;

	private int product_count;

	private String pcImg;

	private String pcImg2;

	private String pcImg3;

	private int cartId;//modelで渡す

	private int creditId;
	
	private int couponId;
	
	private String menberCouponCheck;

	private int  productCount;
	
    private int customId;
	
	private String memory;

	private String hardDisc;

	private String cpu;

	private int customPrice;
	
	private int afterCustomPrice;
	
	private int disCountPriceNew;
	
	private int totalPriceOne;
	
	private boolean couponCheck;

}
