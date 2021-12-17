package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;
import com.example.demo.login.domail.repository.AuctionDataDao;

@Repository
public class AuctionDataDaoJdbcImpl implements AuctionDataDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int auctionDataInsertOne(AuctionDataDTO auctiondatadto,AuctionListingForm form) {
		int result = jdbc.update("insert into auction_data (company,os,product_name,initial_price,detail,img,img2,product_stock,cost)" 
				+ " value(?,?,?,?,?,?,?,?,?)",form.getCompany(),form.getOs(),form.getProductName(),form.getInitialPrice(),form.getDetail(),form.getImg(),form.getImg2(),form.getProductStock(),form.getCost());
		
		return result;
	}
	
	public List<AuctionDataDTO> selectMany() {
		List<Map<String,Object>> map = jdbc.queryForList("select * from auction_data");
		
		List<AuctionDataDTO> auctiondatadtoList = new ArrayList<>();
		for(Map<String,Object> oneMap : map) {
			AuctionDataDTO auctiondatadto = new AuctionDataDTO();
			auctiondatadto.setId((int)oneMap.get("id"));
			auctiondatadto.setCompany((String)oneMap.get("company"));
			auctiondatadto.setOs((String)oneMap.get("os"));
			auctiondatadto.setProductName((String)oneMap.get("product_name"));
			auctiondatadto.setInitialPrice((int)oneMap.get("initial_price"));
			auctiondatadto.setDetail((String)oneMap.get("detail"));
			auctiondatadto.setImg((String)oneMap.get("img"));
			auctiondatadto.setImg2((String)oneMap.get("img2"));
			auctiondatadto.setProductStock((int)oneMap.get("product_stock"));
			auctiondatadto.setCost((int)oneMap.get("cost"));
			auctiondatadto.setListingStopCheck((String)oneMap.get("listing_stop_check"));
			
			auctiondatadtoList.add(auctiondatadto);
		}
		
		return auctiondatadtoList;
	}
}
