package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.auctionPaymentProductForm;
import com.example.demo.login.domail.repository.AuctionProductHistoryDao;

@Service
public class AuctionProductHistoryService {

	@Autowired
	AuctionProductHistoryDao dao;
	
	public int insertOne(int auctionId,int userId,auctionPaymentProductForm form) {
		return dao.insertOne(auctionId,userId,form); 
	}
	
	public int getSelectDeliveryProductIdOne(int auctionIdOne, int userId) {
		return dao.getSelectDeliveryProductIdOne(auctionIdOne,userId);
	}
}
