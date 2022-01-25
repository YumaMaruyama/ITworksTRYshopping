package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.auctionPaymentProductForm;
import com.example.demo.login.domail.repository.AuctionProductHistoryDao;

@Repository
public class AuctionProductHistoryDaoJdbcImpl implements AuctionProductHistoryDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(int auctionId,int userId,auctionPaymentProductForm form) {
		int result = jdbc.update("insert into auction_product_history (user_id,auction_id,delivery_check,digits_3_code,card_name,card_number) value(?,?,?,?,?,?)",userId,auctionId,"発送前",form.getDigits_3_code(),form.getCardName(),form.getCardNumber());
		return result;
	}
	
	public int getSelectDeliveryProductIdOne(int auctionIdOne, int userId) {
		int deliveryCheck = 0;
		
		try {
		int result = jdbc.queryForObject("select auction_product_history.id from auction_product_history where user_id = ? and auction_id = ?",Integer.class,userId,auctionIdOne);
		deliveryCheck = 1;
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			deliveryCheck = 0;
		}
		
		return deliveryCheck;
		
	}
}
