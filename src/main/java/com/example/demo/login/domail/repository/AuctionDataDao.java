package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;

public interface AuctionDataDao {

	public int auctionDataInsertOne(AuctionDataDTO auctiondatadto,AuctionListingForm form);
	
	public List<AuctionDataDTO> selectMany();
}
