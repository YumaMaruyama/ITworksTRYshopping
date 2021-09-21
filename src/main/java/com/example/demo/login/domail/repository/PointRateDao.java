package com.example.demo.login.domail.repository;

import javax.servlet.http.HttpSession;

public interface PointRateDao {

	public int updateOne(HttpSession session);

	public int selectOne(int id);

}
