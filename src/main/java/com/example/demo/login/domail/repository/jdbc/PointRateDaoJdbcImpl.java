package com.example.demo.login.domail.repository.jdbc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.repository.PointRateDao;

@Repository
public class PointRateDaoJdbcImpl implements PointRateDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public int updateOne(HttpSession session) {
		int result = jdbc.update("update point_rate set point_rate = ?",session.getAttribute("pointRate"));
		return result;
	}
	
	public int selectOne(int id) {
		int pointRate = jdbc.queryForObject("select point_rate from point_rate where id = ?",Integer.class,id);
		
		return pointRate;
	}

}
