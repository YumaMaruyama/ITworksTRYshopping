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
		Map<String,Object> map = jdbc.queryForMap("select * from challenge_programming_contract where contract_project_id = ?",productId);
		String teacherName = jdbc.queryForObject("select challenge_programming.my_name from challenge_programming where id = ?",String.class,productId);
		
		ChallengeProgrammingContractDTO challengeProgrammingContractdto = new ChallengeProgrammingContractDTO();
		challengeProgrammingContractdto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingContractdto.setTeacherName((String)map.get(teacherName));
		
		
		return challengeProgrammingContractdto;
	}
}
