package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;
import com.example.demo.login.domail.model.AuctionTenderForm;
import com.example.demo.login.domail.repository.AuctionDataDao;

@Service
public class AuctionDataService {

	@Autowired
	AuctionDataDao dao;
	
	public int auctionDataInsertOne(AuctionDataDTO auctiondatadto,AuctionListingForm form) {
		return dao.auctionDataInsertOne(auctiondatadto,form);
	}
	
	public List<AuctionDataDTO> selectMany() {
		return dao.selectMany();
	}
	
	public AuctionDataDTO selectOne(int auctionId) {
		return dao.selectOne(auctionId);
	}
	
	public int tenderUpdateOne(AuctionTenderForm form,int auctiondataId) {
		return dao.tenderUpdateOne(form,auctiondataId);
	}
	
	public AuctionDataDTO priceSelectOne(int auctiondataId) {
		return dao.priceSelectOne(auctiondataId);
	}
}