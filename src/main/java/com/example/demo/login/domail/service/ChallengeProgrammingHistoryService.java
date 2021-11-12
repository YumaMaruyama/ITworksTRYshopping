package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingHistoryDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingHistoryDao;

@Service
public class ChallengeProgrammingHistoryService {

	@Autowired
	ChallengeProgrammingHistoryDao dao;
	
	public int historyInsertOne(ChallengeProgrammingDTO challengeprogrammingDTO,ChallengeProgrammingHistoryDTO challengeProgrammingHistoryDTO) {
		return dao.historyInsertOne(challengeprogrammingDTO,challengeProgrammingHistoryDTO);
	}
	
	public List<ChallengeProgrammingHistoryDTO> historySelectMany(int userId) {
		return dao.historySelectMany(userId);
	}
}
