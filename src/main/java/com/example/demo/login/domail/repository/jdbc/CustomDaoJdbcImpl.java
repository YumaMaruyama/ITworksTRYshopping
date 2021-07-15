package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.repository.CustomDao;

@Repository
public class CustomDaoJdbcImpl implements CustomDao{

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(int id,int select_id,String memory,String hardDisc,String cpu,int customPrice) {
		
		int result = jdbc.update("insert into custom (product_id,"
				+ " user_id,"
				+ " memory,"
				+ " hard_disc,"
				+ " cpu,"
				+ " custom_price)"
				+ " value(?,?,?,?,?,?)",id,select_id,memory,hardDisc,cpu,customPrice);
		
		return result;
	}
}
