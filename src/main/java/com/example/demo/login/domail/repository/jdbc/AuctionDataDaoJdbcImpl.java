package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.AuctionDataDTO;
import com.example.demo.login.domail.model.AuctionListingForm;
import com.example.demo.login.domail.model.AuctionTenderForm;
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
			try {
				auctiondatadto.setTenderPrice((int)oneMap.get("tender_price"));
				}catch(NullPointerException e) {
					e.printStackTrace();
					auctiondatadto.setTenderPrice(0);
				}
			
			auctiondatadto.setTenderNumber((int)oneMap.get("tender_number"));
			
			auctiondatadtoList.add(auctiondatadto);
		}
		
		return auctiondatadtoList;
	}
	
	public AuctionDataDTO selectOne(int auctionId) {
		
		Map<String,Object> map = jdbc.queryForMap("select * from auction_data where id = ?",auctionId);
		
		
			AuctionDataDTO auctiondatadto = new AuctionDataDTO();
			auctiondatadto.setId((int)map.get("id"));
			auctiondatadto.setCompany((String)map.get("company"));
			auctiondatadto.setOs((String)map.get("os"));
			auctiondatadto.setProductName((String)map.get("product_name"));
			auctiondatadto.setInitialPrice((int)map.get("initial_price"));
			auctiondatadto.setDetail((String)map.get("detail"));
			auctiondatadto.setImg((String)map.get("img"));
			auctiondatadto.setImg2((String)map.get("img2"));
			auctiondatadto.setProductStock((int)map.get("product_stock"));
			auctiondatadto.setCost((int)map.get("cost"));
			auctiondatadto.setListingStopCheck((String)map.get("listing_stop_check"));
			try {
			auctiondatadto.setTenderPrice((int)map.get("tender_price"));
			}catch(NullPointerException e) {
				e.printStackTrace();
				auctiondatadto.setTenderPrice(0);
			}
			
			auctiondatadto.setTenderNumber((int)map.get("tender_number"));
			
		return auctiondatadto;
	}
	
	public int tenderUpdateOne(AuctionTenderForm form,int auctiondataId) {
		jdbc.update("update auction_data set tender_price = ? where id = ?",form.getTenderPrice(),auctiondataId);
		int tenderNumber = jdbc.queryForObject("select auction_data.tender_number from auction_data where id = ?",Integer.class,auctiondataId);
		int tenderNumberUpdate = tenderNumber + 1;
		jdbc.update("update auction_data set tender_number = ? where id = ?",tenderNumberUpdate,auctiondataId);
		
		
		return tenderNumberUpdate;
	}
	
	public AuctionDataDTO priceSelectOne(int auctiondataId) {
		Map<String,Object> map = jdbc.queryForMap("select * from auction_data where id = ?",auctiondataId);
		
		AuctionDataDTO auctiondatadto = new AuctionDataDTO();
		auctiondatadto.setInitialPrice((int)map.get("initial_price"));
		auctiondatadto.setTenderPrice((int)map.get("tender_price"));
		
		return auctiondatadto;
		
	}
}
