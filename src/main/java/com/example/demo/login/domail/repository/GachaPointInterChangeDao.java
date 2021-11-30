package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.GachaPointInterChangeDTO;

public interface GachaPointInterChangeDao {

	public List<GachaPointInterChangeDTO> selectMany();
	
	public GachaPointInterChangeDTO selectOne(int gachaPointProductId);
	
}
