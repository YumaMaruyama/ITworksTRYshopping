package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CancelDTO;
import com.example.demo.login.domail.repository.CancelDao;

@Repository
public class CancelDaoJdbcImpl implements CancelDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName) {
		int result = jdbc.update("insert into cancel (id,"
				+ " purchase_id,"
				+ " product_id,"
				+ " title,"
				+ " content,"
				+ " bank_number,"
				+ " store_name)"
				+ " value(?,?,?,?,?,?)",canceldto.getId(),userId,purchaseId,product_id,title,content,bankNumber,storeName);
		
		
		return result;
				
	}
}
