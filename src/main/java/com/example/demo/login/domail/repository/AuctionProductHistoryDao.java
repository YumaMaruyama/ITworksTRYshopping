package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.auctionPaymentProductForm;

public interface AuctionProductHistoryDao {

	public int insertOne(int auctionId,int userId,auctionPaymentProductForm form);
	
	public int getSelectDeliveryProductIdOne(int auctionIdOne, int userId);
}
