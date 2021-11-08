package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;

public interface ChallengeProgrammingContractDao {

	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId,ChallengeProgrammingMessageDTO challengeProgrammingMessagedto);

	public int duplicateCheck(int userId);

	public ChallengeProgrammingContractDTO teacherMessege1Select(int productId);
	
	public ChallengeProgrammingContractDTO tm1Mm1Select(int productId);
	
	public ChallengeProgrammingContractDTO tm2Mm2Select(int productId);
	
	public ChallengeProgrammingContractDTO tm3Mm3select(int productId);
	
	public int chatComplete(int productId);
	
	public int selectChatCheck(int productId);
	
	public int lessonDayInsertOne(String lessonDay,int productId);
	
	public String lessonDaySelectOne(int productId);

	public int belongingsCheckInsertOne(int productId);
	
	public String belongngsSelectOne(int productId);
	
	public int locationConfirmationInsertOne(int productId);
	
	public String locationSelectOne(int productId);

	public int lessonCheckInsertOne(int productId);
	
	public String lessonCheckSelectOne(int productId);
	
	public int lessonCheckUpdateOne(int productId);
	
	public int startDateInsertOne(int productId,String simpleDate);
	
	public int EndDateInsertOne(int productId,String simpleDate);
	
	public ChallengeProgrammingContractDTO startAndEndDateSelectOne(int productId);
}
