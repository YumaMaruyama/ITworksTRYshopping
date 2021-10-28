package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;

public interface ChallengeProgrammingContractDao {

	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId,ChallengeProgrammingMessageDTO challengeProgrammingMessagedto);

	public int duplicateCheck(int userId);

	public ChallengeProgrammingContractDTO teacherMessege1Select(int productId);
}
