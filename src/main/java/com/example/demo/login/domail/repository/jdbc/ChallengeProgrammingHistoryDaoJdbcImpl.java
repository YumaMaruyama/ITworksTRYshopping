package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.model.ChallengeProgrammingHistoryDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingHistoryDao;

@Repository
public class ChallengeProgrammingHistoryDaoJdbcImpl implements ChallengeProgrammingHistoryDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int historyInsertOne(ChallengeProgrammingDTO challengeprogrammingDTO,ChallengeProgrammingHistoryDTO challengeProgrammingHistoryDTO) {
		int result = jdbc.update("insert into challenge_programming_history (id,"
				+ " user_id,"
				+ " price,"
				+ " teacher_name,"
				+ " title,"
				+ " content,"
				+ " start_date,"
				+ " end_date)"
				+ " value(?,?,?,?,?,?,?,?)",challengeProgrammingHistoryDTO.getId(),challengeprogrammingDTO.getContractUserId(),challengeprogrammingDTO.getPrice(),challengeprogrammingDTO.getMyName(),challengeprogrammingDTO.getTitle(),challengeprogrammingDTO.getContent(),challengeprogrammingDTO.getStartDate(),challengeprogrammingDTO.getEndDate());
		
		return result;
	}
	
	
	
}
