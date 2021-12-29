package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuctionTenderDataDTO {

	private int id;
	
	private int auctionDataId;
	
	private int userId;
	
	private String userName;
	
	private int tenderPrice;
	
	private Date auctionTenderDate;
}
