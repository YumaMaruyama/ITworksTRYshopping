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

	public int clearingInsertOne(CreditDTO creditdto,int select_id,int totalPrice) {

		int result = jdbc.update("insert into credit (id,"
				+ " cardName,"
				+ " cardNumber,"
				+ " user_id,"
				+ " digits_3_code,"
				+ " payment_price)"
				+ " value(?,?,?,?,?,?)",creditdto.getId(),creditdto.getCardName(),creditdto.getCardNumber(),select_id,creditdto.getDigits_3_code(),totalPrice);

		return result;

	}

	public CreditDTO clearingSelectOne(int getId) {

		Map<String,Object> map = jdbc.queryForMap("select * credit where user_id = ?",getId);

		CreditDTO creditdto = new CreditDTO();
		creditdto.setDigits_3_code((String)map.get("digits_3_code"));
		creditdto.setCardName((String)map.get("cart_name"));
		creditdto.setCardNumber((String)map.get("cart_number"));

		return creditdto;

	}

	public int selectMaxId() {
		int selectMaxId = jdbc.queryForObject("select max(credit.id) from credit",Integer.class);

		return selectMaxId;
	}
}
