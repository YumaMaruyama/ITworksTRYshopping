package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.repository.ProductGoodDao;

@Repository
public class ProductGoodDaoJdbcImpl implements ProductGoodDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(int userId,int productId) {
		int result = jdbc.update("insert into product_good (user_id,product_id) value(?,?)",userId,productId);
		return result;
	}
	
	public List<Integer> selectMany(int userId) {
		List<Map<String,Object>> map = jdbc.queryForList("select product_good.product_id from product_good where user_id = ?",userId);
		
		List<Integer> productId = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			int i = (int) oneMap.get("product_id");
			productId.add(i);
		}
		
		return productId;
		
	}
	
	public int goodCheck(int userId,int productId) {
		int goodCheck = 0;
		try {
		int result = jdbc.queryForObject("select product_good.id from product_good where user_id = ? and product_id = ?",Integer.class,userId,productId);
		if(result > 0) {
			goodCheck = 1;
		}
		
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			goodCheck = 0;
		}
		
		return goodCheck;
	}
	
	public int deleteOne(int userId,int productId) {
		int result = jdbc.update("delete from product_good where user_id = ? and product_id = ?",userId,productId);
		
		return result;
	}
	
}
