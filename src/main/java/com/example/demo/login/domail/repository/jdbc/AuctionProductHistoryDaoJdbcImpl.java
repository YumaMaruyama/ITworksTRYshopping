package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.auctionPaymentProductForm;
import com.example.demo.login.domail.repository.AuctionProductHistoryDao;

@Repository
public class AuctionProductHistoryDaoJdbcImpl implements AuctionProductHistoryDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(int auctionId,int userId,auctionPaymentProductForm form) {
		int result = jdbc.update("insert into auction_product_history (user_id,auction_id,delivery_check) value(?,?,?)",userId,auctionId,"発送前");
		return result;
	}
}
