package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.repository.CreditDao;
@Repository
public class CreditDaoJdbcImpl implements CreditDao{

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(CreditDTO creditdto,String getName) {
		int rowNumber = jdbc.update("insert into credit (id,"
				+ " registration_date,"
				+ " digits_3_code,"
				+ " cardName,"
				+ " cardNumber,"
				+ " user_id)"
				+ " value(?,?,?,?,?,?)",creditdto.getId(),creditdto.getRegistration_date(),creditdto.getDigits_3_code(),creditdto.getCardName(),creditdto.getCardNumber(),getName);

	return rowNumber;
	}

	public CreditDTO selectOne(String getName) {
		Map<String,Object> map = jdbc.queryForMap("select * from credit where id = ?",getName);

		CreditDTO creditdto = new CreditDTO();
		creditdto.setDigits_3_code((String)map.get("digits_3_code"));
		creditdto.setCardName((String)map.get("cardName"));
		creditdto.setCardNumber((String)map.get("cardNumber"));

		return creditdto;

	}
}
