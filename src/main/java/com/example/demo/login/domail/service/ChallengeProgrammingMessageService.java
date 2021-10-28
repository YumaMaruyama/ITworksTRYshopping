package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingMessageDao;

@Service
public class ChallengeProgrammingMessageService {

	@Autowired
	ChallengeProgrammingMessageDao dao;
	
	public ChallengeProgrammingMessageDTO selectOne() {
		return dao.selectOne();
	}
}
