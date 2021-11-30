package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.GachaPointInterChangeDTO;
import com.example.demo.login.domail.repository.GachaPointInterChangeDao;

@Service
public class GachaPointInterChangeService {

	@Autowired
	GachaPointInterChangeDao dao;
	
	public List<GachaPointInterChangeDTO> selectMany() {
		return dao.selectMany();
	}
	
	public GachaPointInterChangeDTO selectOne(int gachaPointProductId) {
		return dao.selectOne(gachaPointProductId);
	}
}
