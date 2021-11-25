package com.example.demo.login.domail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.GachaDTO;
import com.example.demo.login.domail.repository.GachaDao;

@Service
public class GachaService {

	@Autowired
	GachaDao dao;
	
	public int gachaTurnInsertOne(GachaDTO gachadto,int userId,Date nowDate) {
		return dao.gachaTurnInsertOne(gachadto,userId,nowDate);
	}
	
	public Date gachaTurnedCheck(int userId) {
		return dao.gachaTurnedCheck(userId);
	}
}
