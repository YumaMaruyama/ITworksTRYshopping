package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingDao;

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
			challengeProgrammingdto.setImg((String)oneMap.get("img"));
			
			challengeProgrammingList.add(challengeProgrammingdto);
		}
		
		return challengeProgrammingList;
	}
	
	public ChallengeProgrammingDTO projectSelectOne(int projectId) {
		Map<String,Object> map = jdbc.queryForMap("select * from challenge_programming where id = ?",projectId);
		
		ChallengeProgrammingDTO challengeProgrammingdto = new ChallengeProgrammingDTO();
		challengeProgrammingdto.setId((int)map.get("id"));
		challengeProgrammingdto.setTitle((String)map.get("title"));
		challengeProgrammingdto.setContent((String)map.get("content"));
		challengeProgrammingdto.setPrice((int)map.get("price"));
		challengeProgrammingdto.setWorkingTimes((int)map.get("workingTime"));
		challengeProgrammingdto.setMyName((String)map.get("my_name"));
		challengeProgrammingdto.setPosition((String)map.get("position"));
		challengeProgrammingdto.setBirthplace((String)map.get("birthplace"));
		challengeProgrammingdto.setNationality((String)map.get("nationality"));
		challengeProgrammingdto.setEducationalBackground((String)map.get("educational_background"));
		challengeProgrammingdto.setBelongings((String)map.get("belongings"));
		challengeProgrammingdto.setSteup((String)map.get("steup"));
		challengeProgrammingdto.setVoiceTeacher((String)map.get("voice_teacher"));
		challengeProgrammingdto.setLearnSpace((String)map.get("learn_space"));
		challengeProgrammingdto.setFixableTimeFrom((Date)map.get("fixable_time_from"));
		challengeProgrammingdto.setFixableTimeTo((Date)map.get("fixable_time_to"));
		challengeProgrammingdto.setImg((String)map.get("img"));
		try {
		challengeProgrammingdto.setContractUserId((int)map.get("contract_user_id"));
		}catch(NullPointerException e) {
			System.out.println("testcatch");
			e.printStackTrace();
			challengeProgrammingdto.setContractUserId(0);
		}
		return challengeProgrammingdto;
	}
	
	public int contractUpdate(int userId,int productId) {
		int result = jdbc.update("update challenge_programming set contract_user_id = ? where id = ?",userId,productId);
		
		return result;
	}
	
	public ChallengeProgrammingDTO selectBelongings(int productId) {
		Map<String,Object> map = jdbc.queryForMap("select * from challenge_programming where id = ?",productId);
		
		ChallengeProgrammingDTO challengeprogrammingdto = new ChallengeProgrammingDTO();
		
		challengeprogrammingdto.setBelongings((String)map.get("belongings"));
		challengeprogrammingdto.setSteup((String)map.get("steup"));
		
		return challengeprogrammingdto;
	}
	
	public int updateOne(int productId) {
		int result = jdbc.update("update challenge_programming set contract_user_id = null where id = ?",productId);
		
		return result;
	}
	
	
	
	
}
