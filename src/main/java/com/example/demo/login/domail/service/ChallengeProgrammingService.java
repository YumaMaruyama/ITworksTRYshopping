package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.repository.jdbc.ChallengeProgrammingDao;

@Service
public class ChallengeProgrammingService {

	@Autowired
	ChallengeProgrammingDao dao;
	
	public List<ChallengeProgrammingDTO> projectSelectMany() {
		return dao.projectSelectMany();
	}
}
