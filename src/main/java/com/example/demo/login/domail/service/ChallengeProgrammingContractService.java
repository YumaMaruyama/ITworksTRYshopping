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
	
	public ChallengeProgrammingContractDTO tm2Mm2Select(int productId) {
		return dao.tm2Mm2Select(productId);
	}
	
	public ChallengeProgrammingContractDTO tm3Mm3select(int productId) {
		return dao.tm3Mm3select(productId);
	}
	
	public int chatComplete(int productId) {
		return dao.chatComplete(productId);
	}
	
	public int selectChatCheck(int productId) {
		return dao.selectChatCheck(productId);
	}
	
	public int lessonDayInsertOne(String lessonDay,int productId) {
		return dao.lessonDayInsertOne(lessonDay,productId);
	}
	
	public String lessonDaySelectOne(int productId) {
		return dao.lessonDaySelectOne(productId);
	}
	
	public int belongingsCheckInsertOne(int productId) {
		return dao.belongingsCheckInsertOne(productId);
	}
	
	public String belongngsSelectOne(int productId) {
		return dao.belongngsSelectOne(productId);
	}
	
	public int locationConfirmationInsertOne(int productId) {
		return dao.locationConfirmationInsertOne(productId);
	}
	
	public String locationSelectOne(int productId) {
		return dao.locationSelectOne(productId);
	}
	
	public int lessonCheckInsertOne(int productId) {
		return dao.lessonCheckInsertOne(productId);
	}
	
	public String lessonCheckSelectOne(int productId) {
		return dao.lessonCheckSelectOne(productId);
	}
	
	public int lessonCheckUpdateOne(int productId) {
		return dao.lessonCheckUpdateOne(productId);
	}
	
	public int startDateInsertOne(int productId,String simpleDate) {
		return dao.startDateInsertOne(productId,simpleDate);
	}
	
	public int EndDateInsertOne(int productId,String simpleDate) {
		return dao.EndDateInsertOne(productId,simpleDate);
	}
	
	public ChallengeProgrammingContractDTO startAndEndDateSelectOne(int productId) {
		return dao.startAndEndDateSelectOne(productId);
	}
}
