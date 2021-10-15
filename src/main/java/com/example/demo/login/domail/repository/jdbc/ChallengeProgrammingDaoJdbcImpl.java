package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;

@Repository
public class ChallengeProgrammingDaoJdbcImpl implements ChallengeProgrammingDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<ChallengeProgrammingDTO> projectSelectMany() {
		
		List<Map<String,Object>> map = jdbc.queryForList("select * from challenge_programming");
		
		
		
		List<ChallengeProgrammingDTO> challengeProgrammingList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			ChallengeProgrammingDTO challengeProgrammingdto = new ChallengeProgrammingDTO();
			challengeProgrammingdto.setId((int)oneMap.get("id"));
			challengeProgrammingdto.setTitle((String)oneMap.get("title"));
			challengeProgrammingdto.setContent((String)oneMap.get("content"));
			challengeProgrammingdto.setPrice((int)oneMap.get("price"));
			challengeProgrammingdto.setWorkingTimes((int)oneMap.get("workingTime"));
			challengeProgrammingdto.setMyName((String)oneMap.get("my_name"));
			challengeProgrammingdto.setPosition((String)oneMap.get("position"));
			challengeProgrammingdto.setFixableTimeFrom((Date)oneMap.get("fixable_time_from"));
			challengeProgrammingdto.setFixableTimeTo((Date)oneMap.get("fixable_time_to"));
			challengeProgrammingdto.setImg((String)oneMap.get("img"));;
			
			challengeProgrammingList.add(challengeProgrammingdto);
		}
		
		return challengeProgrammingList;
	}
	
	
}
