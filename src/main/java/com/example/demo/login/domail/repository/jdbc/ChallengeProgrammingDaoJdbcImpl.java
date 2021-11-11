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
	
	public ChallengeProgrammingDTO lessonSelectOne(int productId) {
		
Map<String,Object> map = jdbc.queryForMap("select challenge_programming.id,challenge_programming.title,challenge_programming.content,challenge_programming.price,challenge_programming.workingTime,challenge_programming.my_name,challenge_programming.position,challenge_programming.birthplace,challenge_programming.nationality,challenge_programming.educational_background,challenge_programming.belongings,challenge_programming.steup,challenge_programming.voice_teacher,challenge_programming.learn_space,challenge_programming.fixable_time_from,challenge_programming.fixable_time_to,challenge_programming.img,challenge_programming.contract_user_id,challenge_programming_contract.start_date,challenge_programming_contract.end_date from challenge_programming join challenge_programming_contract on challenge_programming.id = challenge_programming_contract.contract_project_id where challenge_programming.id = ?",productId);
		
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
		challengeProgrammingdto.setStartDate((Date)map.get("start_date"));
		challengeProgrammingdto.setEndDate((Date)map.get("end_Date"));
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
