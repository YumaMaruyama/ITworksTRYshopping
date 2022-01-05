package com.example.demo.login.domail.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AuctionListingForm {

	private int id;
	
	private String company;
	
	private String os;
	
	private String productName;
	
	private int initialPrice;
	
	private String detail;
	
	private String img;
	
	private String img2;
	
	private int productStock;
	
	private int cost;
	
	private String listingStopCheck;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tenderEndDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String newTenderEndDate;
}
