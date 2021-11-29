package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.repository.GachaPointsDao;

@Repository
public class GachaPointsDaoJdbcImpl implements GachaPointsDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int dailyGachaGetPointAdd(int userId,int totalPoint) {
		//ガチャを一回以上回したことがあるかチェック
		try {
			int selectResult = jdbc.queryForObject("select gacha_points.user_id from gacha_points where user_id = ?",Integer.class,userId);
			int getTotalPoint = jdbc.queryForObject("select gacha_points.point from gacha_points where user_id = ?",Integer.class,userId);
			int pointAddResult = getTotalPoint + totalPoint;
			System.out.println("update");
		    jdbc.update("update gacha_points set point = ? where user_id = ?",pointAddResult,userId);
		
		}catch(EmptyResultDataAccessException e) {
			System.out.println("insert");
			int insertResult = jdbc.update("insert into gacha_points (user_id,point) value(?,?)",userId,totalPoint);
		}
		
		return 0;
	}
	
	public int selectPointOne(int userId) {
		int point = jdbc.queryForObject("select gacha_points.point from gacha_points where user_id = ?",Integer.class,userId);
		
		return point;
	}
	
}
