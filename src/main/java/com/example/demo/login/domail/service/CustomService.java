package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.CustomDao;

@Service
public class CustomService {

	@Autowired
	CustomDao dao;
	
	public int insertOne(int id,int select_id,String memory,String hardDisc,String cpu,int customPrice) {
		return dao.insertOne(id,select_id,memory,hardDisc,cpu,customPrice);
	}
}
