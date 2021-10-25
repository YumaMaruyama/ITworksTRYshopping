package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingContractDao;

@Repository
public class ChallengeProgrammingContractDaoJdbcImpl implements ChallengeProgrammingContractDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId) {
		
		int result = jdbc.update("insert into challenge_programming_contract (id,"
				+ " user_id,"
				+ " mail_address,"
				+ " phone_number,"
				+ " contract_project_id)"
				+ " value(?,?,?,?,?)",challengeProgrammingContractdto.getId(),userId,mailAddress,phoneNumber,productId);
		
		return result;
	}
	
	public int duplicateCheck(int userId) {
		
		int result = 0;
		
		try {
			int selectUserId = jdbc.queryForObject("select challenge_programming_contract.user_id from challenge_programming_contract where id = ?",Integer.class,userId);
		}catch(NullPointerException | EmptyResultDataAccessException e) {
			e.printStackTrace();
			result = 1;
		}
		
	return result;
	}
}
