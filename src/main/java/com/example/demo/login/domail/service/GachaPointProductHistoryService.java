package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.GachaPointProductHistoryDTO;
import com.example.demo.login.domail.repository.GachaPointProductHistoryDao;

@Service
public class GachaPointProductHistoryService {

	@Autowired
	GachaPointProductHistoryDao dao;
	
	public int productHistoryInsertOne(int userId,int gachaPointProductId) {
		return dao.productHistoryInsertOne(userId,gachaPointProductId);
	}
	
	public List<GachaPointProductHistoryDTO> productHistorySelectOne(int userId) {
		return dao.gachaPointProductHistorydtoList(userId);
	}
	
	public List<GachaPointProductHistoryDTO> productHistorySelectMany() {
		return dao.productHistorySelectMany();
	}
}
