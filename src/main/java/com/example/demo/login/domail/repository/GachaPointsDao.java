package com.example.demo.login.domail.repository;

public interface GachaPointsDao {

	public int dailyGachaGetPointAdd(int userId,int totalPoint);
	
	public int selectPointOne(int userId);
}
