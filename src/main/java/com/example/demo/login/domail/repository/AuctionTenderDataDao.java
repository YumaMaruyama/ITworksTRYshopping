package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.AuctionTenderForm;

public interface AuctionTenderDataDao {

	public int insertOne(AuctionTenderForm form,int auctiondataId,int userId);
}
