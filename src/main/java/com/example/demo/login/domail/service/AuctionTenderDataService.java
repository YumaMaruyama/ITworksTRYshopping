package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.AuctionTenderForm;
import com.example.demo.login.domail.repository.AuctionTenderDataDao;

@Service
public class AuctionTenderDataService {

	@Autowired
	AuctionTenderDataDao dao;
	
	public int insertOne(AuctionTenderForm form,int auctiondataId,int userId) {
		return dao.insertOne(form,auctiondataId,userId);
	}
}
