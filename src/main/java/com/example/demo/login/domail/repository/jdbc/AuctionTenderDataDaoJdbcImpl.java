package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.AuctionTenderForm;
import com.example.demo.login.domail.repository.AuctionTenderDataDao;

@Repository
public class AuctionTenderDataDaoJdbcImpl implements AuctionTenderDataDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(AuctionTenderForm form,int auctiondataId,int userId) {
		int result = jdbc.update("insert into auction_tender_data (auction_data_id,user_id,tender_price) value(?,?,?)",auctiondataId,form.getNewTenderPrice(),userId);
		
		return result;
	}
}
