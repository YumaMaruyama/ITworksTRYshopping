package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseDaoJdbcImpl {

	@Autowired
	JdbcTemplate jdbc;

	public int insert(int purchaseId,int purchaseCount,int totalPrice,int select_id) {

	}
}
