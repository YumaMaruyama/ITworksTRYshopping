package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.AuctionTenderDataDTO;
import com.example.demo.login.domail.model.AuctionTenderForm;

public interface AuctionTenderDataDao {

	public int insertOne(AuctionTenderForm form,int auctiondataId,int userId);
	
	public List<AuctionTenderDataDTO> selectMany();
}
