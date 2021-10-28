package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingContractDao;

@Service
public class ChallengeProgrammingContractService {

	@Autowired
	ChallengeProgrammingContractDao dao;
	
	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId,ChallengeProgrammingMessageDTO challengeProgrammingMessagedto) {
		return dao.insertOne(challengeProgrammingContractdto,mailAddress,phoneNumber,userId,productId,challengeProgrammingMessagedto);
	}
	
	public int duplicateCheck(int userId) {
		return dao.duplicateCheck(userId);
	}
	
	public ChallengeProgrammingContractDTO teacherMessege1Select(int productId) {
		return dao.teacherMessege1Select(productId);
	}
	
	public ChallengeProgrammingContractDTO tm1Mm1Select(int productId) {
		return dao.tm1Mm1Select(productId);
	}
}
