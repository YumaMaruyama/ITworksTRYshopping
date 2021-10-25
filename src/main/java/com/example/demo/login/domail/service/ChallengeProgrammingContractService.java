package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingContractDao;

@Service
public class ChallengeProgrammingContractService {

	@Autowired
	ChallengeProgrammingContractDao dao;
	
	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId) {
		return dao.insertOne(challengeProgrammingContractdto,mailAddress,phoneNumber,userId,productId);
	}
	
	public int duplicateCheck(int userId) {
		return dao.duplicateCheck(userId);
	}
}
