package com.example.demo.login.domail.repository.jdbc;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.AuctionTenderDataDTO;
import com.example.demo.login.domail.model.AuctionTenderForm;
import com.example.demo.login.domail.repository.AuctionTenderDataDao;

@Repository
public class AuctionTenderDataDaoJdbcImpl implements AuctionTenderDataDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(AuctionTenderForm form,int auctiondataId,int userId) {
		int result = jdbc.update("insert into auction_tender_data (auction_data_id,user_id,tender_price,status) value(?,?,?,?)",auctiondataId,userId,form.getNewTenderPrice(),"現在の落札者です");
		
		return result;
	}
	
	public List<AuctionTenderDataDTO> selectMany(int auctionId) {
		List<Map<String,Object>> map = jdbc.queryForList("select * from auction_tender_data where auction_data_id = ? order by auction_tender_date desc",auctionId);
		
		List<AuctionTenderDataDTO> auctiontenderdatadtoList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			AuctionTenderDataDTO auctiontenderdatadto = new AuctionTenderDataDTO();
			auctiontenderdatadto.setId((int)oneMap.get("id"));
			auctiontenderdatadto.setAuctionDataId((int)oneMap.get("auction_data_id"));
			auctiontenderdatadto.setUserId((int)oneMap.get("user_id"));
			auctiontenderdatadto.setTenderPrice((int)oneMap.get("tender_price"));
			auctiontenderdatadto.setAuctionTenderDate((Date)oneMap.get("auction_tender_date"));
			auctiontenderdatadto.setStatus((String)oneMap.get("status"));
			
			
			auctiontenderdatadtoList.add(auctiontenderdatadto);
			
		}
		
		return auctiontenderdatadtoList;
	} 
	
	public int statusUpdate(int auctiondataId) {
		int result = jdbc.update("update auction_tender_data set status = ? where auction_data_id = ?","抜かされました",auctiondataId);
		
		return result;
	}
}
