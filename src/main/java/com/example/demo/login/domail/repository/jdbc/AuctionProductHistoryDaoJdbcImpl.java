package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.AuctionProductHistoryDTO;
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
	
	public List<AuctionProductHistoryDTO> selectAuctionProductMany() {
		
		List<Map<String,Object>> map = jdbc.queryForList("select auction_product_history.id,auction_product_history.user_id,auction_product_history.auction_id,auction_product_history.purchase_date,auction_product_history.delivery_check,users.user_name,usege_users.address,auction_data.product_name,auction_data.tender_price,auction_data.img from auction_product_history join users on users.id = auction_product_history.user_id join usege_users on usege_users.user_id = auction_product_history.user_id join auction_data on auction_product_history.auction_id = auction_data.id");
		
		List<AuctionProductHistoryDTO> auctionProductHistoryDtoList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			AuctionProductHistoryDTO auctionProductHisotoryDtoOne = new AuctionProductHistoryDTO();
			
			auctionProductHisotoryDtoOne.setId((int)oneMap.get("id"));
			auctionProductHisotoryDtoOne.setUserId((int)oneMap.get("user_id"));
			auctionProductHisotoryDtoOne.setAuctionId((int)oneMap.get("auction_id"));
			auctionProductHisotoryDtoOne.setPurchaseDate((Date)oneMap.get("purchase_date"));
			auctionProductHisotoryDtoOne.setDeliveryCheck((String)oneMap.get("delivery_check"));
			auctionProductHisotoryDtoOne.setUserName((String)oneMap.get("user_name"));
			auctionProductHisotoryDtoOne.setAddress((String)oneMap.get("address"));
			auctionProductHisotoryDtoOne.setTenderPrice((int)oneMap.get("tender_price"));
			auctionProductHisotoryDtoOne.setProductName((String)oneMap.get("product_name"));
			auctionProductHisotoryDtoOne.setImg((String)oneMap.get("img"));
			
			auctionProductHistoryDtoList.add(auctionProductHisotoryDtoOne);
		}
		
		return auctionProductHistoryDtoList;
	
	
	}
	
	public int deliveryUpdateOne(int auctionProductHistoryId) {
		int result = jdbc.update("update auction_product_history set delivery_check = '発送済' where id = ?",auctionProductHistoryId);
		
		return result;
	}
}
