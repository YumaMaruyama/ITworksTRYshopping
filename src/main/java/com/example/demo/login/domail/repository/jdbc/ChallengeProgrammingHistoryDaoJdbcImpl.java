package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	public List<ChallengeProgrammingHistoryDTO> historySelectMany(int userId) {
		List<Map<String,Object>> mapList = jdbc.queryForList("select * from challenge_programming_history where user_id = ?",userId);
		
		List<ChallengeProgrammingHistoryDTO> challengeprogramminghistoryDTOList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : mapList) {
			ChallengeProgrammingHistoryDTO challengeprogramminghistoryDTO = new ChallengeProgrammingHistoryDTO();
			challengeprogramminghistoryDTO.setId((int)oneMap.get("id"));
			challengeprogramminghistoryDTO.setUserId((int)oneMap.get("user_id"));
			challengeprogramminghistoryDTO.setPrice((int)oneMap.get("price"));
			challengeprogramminghistoryDTO.setTeacherName((String)oneMap.get("teacher_name"));
			challengeprogramminghistoryDTO.setTitle((String)oneMap.get("title"));
			challengeprogramminghistoryDTO.setContent((String)oneMap.get("content"));
			challengeprogramminghistoryDTO.setStartDate((Date)oneMap.get("start_date"));
			challengeprogramminghistoryDTO.setEndDate((Date)oneMap.get("end_date"));
			challengeprogramminghistoryDTOList.add(challengeprogramminghistoryDTO);
			
		}
		
		return challengeprogramminghistoryDTOList;
	}
	
	
	
}
