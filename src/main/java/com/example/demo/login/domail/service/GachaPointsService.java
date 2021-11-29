package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.GachaPointsDao;

@Service
public class GachaPointsService {

	@Autowired
	GachaPointsDao dao;
	
	public int dailyGachaGetPointAdd(int userId,int totalPoint) {
		return dao.dailyGachaGetPointAdd(userId,totalPoint);
	}
	
	public int selectPointOne(int userId) {
		return dao.selectPointOne(userId);
	}
}
