package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.GachaPointProductHistoryDTO;

public interface GachaPointProductHistoryDao {

	public int productHistoryInsertOne(int userId,int gachaPointProductId);
	
	public List<GachaPointProductHistoryDTO> gachaPointProductHistorydtoList(int userId);
}
