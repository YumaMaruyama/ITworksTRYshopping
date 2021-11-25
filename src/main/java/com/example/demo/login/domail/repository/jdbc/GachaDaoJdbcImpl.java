package com.example.demo.login.domail.repository.jdbc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.GachaDTO;
import com.example.demo.login.domail.repository.GachaDao;

@Repository
public class GachaDaoJdbcImpl implements GachaDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int gachaTurnInsertOne(GachaDTO gachadto,int userId,Date nowDate) {
		
		int result = jdbc.update("insert into gacha (id,"
				+ " user_id,"
				+ " gacha_date)"
				+ " value(?,?,?)",gachadto.getId(),userId,nowDate);
		
		return result;
	}
	
	public Date gachaTurnedCheck(int userId) {
		
		Date gachaTurnDate = null;
		int newGachaTurnDateId = 0;
		try {
		 newGachaTurnDateId =  jdbc.queryForObject("select max(id) from gacha where user_id = ?",Integer.class,userId);
		gachaTurnDate = jdbc.queryForObject("select date_format(gacha.gacha_date, '%Y-%m-%d') from gacha where id = ?",Date.class,newGachaTurnDateId);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return gachaTurnDate;
	}
}
