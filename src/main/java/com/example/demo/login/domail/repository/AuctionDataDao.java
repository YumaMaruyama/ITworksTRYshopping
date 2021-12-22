package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;
import com.example.demo.login.domail.model.AuctionTenderForm;

public interface AuctionDataDao {

	public int auctionDataInsertOne(AuctionDataDTO auctiondatadto,AuctionListingForm form);
	
	public List<AuctionDataDTO> selectMany();
	
	public AuctionDataDTO selectOne(int auctionId);
	
	public int tenderUpdateOne(AuctionTenderForm form,int auctiondataId);
	
	public AuctionDataDTO priceSelectOne(int auctiondataId);
}
