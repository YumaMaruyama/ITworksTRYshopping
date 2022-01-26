package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.AuctionProductHistoryDTO;
import com.example.demo.login.domail.model.auctionPaymentProductForm;

public interface AuctionProductHistoryDao {

	public int insertOne(int auctionId,int userId,auctionPaymentProductForm form);
	
	public int getSelectDeliveryProductIdOne(int auctionIdOne, int userId);
	
	public List<AuctionProductHistoryDTO> selectAuctionProductMany();
	
	public int deliveryUpdateOne(int auctionProductHistoryId);
}
