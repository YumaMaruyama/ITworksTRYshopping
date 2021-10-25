package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;

public interface ChallengeProgrammingContractDao {

	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId);

	public int duplicateCheck(int userId);
}
