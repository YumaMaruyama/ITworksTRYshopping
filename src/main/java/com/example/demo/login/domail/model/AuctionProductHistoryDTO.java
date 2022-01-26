package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionProductHistoryDTO {

	private int id;//
	
	private int userId;//
	
	private int auctionId;//
	
	private Date purchaseDate;//
	
	private String deliveryCheck;//
	
	private String deliveryCheckOk;
	
	private String userName;//
	
	private String address;//
	
	private int tenderPrice;//
	
	private Date auctionTenderDate;//
	
	private String productName;//
	
	private String img;//
	
}
