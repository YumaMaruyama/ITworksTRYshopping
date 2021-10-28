package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingContractDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingContractDao;

@Repository
public class ChallengeProgrammingContractDaoJdbcImpl implements ChallengeProgrammingContractDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(ChallengeProgrammingContractDTO challengeProgrammingContractdto,String mailAddress, String phoneNumber, int userId, int productId,ChallengeProgrammingMessageDTO challengeProgrammingMessagedto) {
		
		int result = jdbc.update("insert into challenge_programming_contract (id,"
				+ " user_id,"
				+ " mail_address,"
				+ " phone_number,"
				+ " contract_project_id,"
				+ " teacher_message1,"
				+ " teacher_message2,"
				+ " teacher_message3,"
				+ " my_message1,"
				+ " my_message2,"
				+ " my_message3)"
				+ " value(?,?,?,?,?,?,?,?,?,?,?)",challengeProgrammingContractdto.getId(),userId,mailAddress,phoneNumber,productId,challengeProgrammingMessagedto.getTeacherMessage1(),challengeProgrammingMessagedto.getTeacherMessage2(),challengeProgrammingMessagedto.getTeacherMessage3(),challengeProgrammingMessagedto.getMyMessage1(),challengeProgrammingMessagedto.getMyMessage2(),challengeProgrammingMessagedto.getMyMessage3());
		
		return result;
	}
	
	public int duplicateCheck(int userId) {
		
		int result = 0;
		
		try {
			int selectUserId = jdbc.queryForObject("select challenge_programming_contract.user_id from challenge_programming_contract where user_id = ?",Integer.class,userId);
			result = 1;
		}catch(NullPointerException | EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
	return result;
	}
	
	public ChallengeProgrammingContractDTO teacherMessege1Select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,challenge_programming.my_name from challenge_programming_contract join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		
		
		return challengeProgrammingContractdto;
	}
	
	public ChallengeProgrammingContractDTO tm1Mm1Select(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_contract.id,challenge_programming_contract.user_id,challenge_programming_contract.phone_number,challenge_programming_contract.contract_date,challenge_programming_contract.contract_project_id,challenge_programming_contract.mail_address,challenge_programming_contract.Teacher_message1,challenge_programming_contract.Teacher_message2,challenge_programming_contract.Teacher_message3,challenge_programming_contract.my_message1,challenge_programming_contract.my_message2,challenge_programming_contract.my_message3,users.user_name,challenge_programming.my_name from challenge_programming_contract join users on challenge_programming_contract.user_id = users.id join challenge_programming on challenge_programming_contract.contract_project_id = challenge_programming.id where contract_project_id = ?",productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherMessage2((String)map.get("teacher_message2"));
		challengeProgrammingContractdto.setMyMessage1((String)map.get("my_message1"));
		challengeProgrammingContractdto.setTeacherName((String)map.get("my_name"));
		challengeProgrammingContractdto.setUserName((String)map.get("user_name"));
		
		return challengeProgrammingContractdto;
	}
}
