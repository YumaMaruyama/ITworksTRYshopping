package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingDao;

@Service
public class ChallengeProgrammingService {

	@Autowired
	ChallengeProgrammingDao dao;
	
	public List<ChallengeProgrammingDTO> projectSelectMany() {
		return dao.projectSelectMany();
	}
	
	public ChallengeProgrammingDTO projectSelectOne(int projectId) {
		return dao.projectSelectOne(projectId);
	}
	
	public int contractUpdate(int userId,int productId) {
		return dao.contractUpdate(userId,productId);
	}
	
	public ChallengeProgrammingDTO selectBelongings(int productId) {
		return dao.selectBelongings(productId);
	}
}
