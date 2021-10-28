package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingMessageDTO;
import com.example.demo.login.domail.repository.ChallengeProgrammingMessageDao;

@Repository
public class ChallengeProgrammingMessageDaoJdbcImpl implements ChallengeProgrammingMessageDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public ChallengeProgrammingMessageDTO selectOne() {
		Map<String,Object> map = jdbc.queryForMap("select * from challenge_programming_message");
		
		ChallengeProgrammingMessageDTO challengeProgrammingMessagedto = new ChallengeProgrammingMessageDTO();
		challengeProgrammingMessagedto.setId((int)map.get("id"));
		challengeProgrammingMessagedto.setTeacherMessage1((String)map.get("teacher_message1"));
		challengeProgrammingMessagedto.setTeacherMessage2((String)map.get("teacher_message2"));
		challengeProgrammingMessagedto.setTeacherMessage3((String)map.get("teacher_message3"));
		challengeProgrammingMessagedto.setMyMessage1((String)map.get("my_message1"));
		challengeProgrammingMessagedto.setMyMessage2((String)map.get("my_message2"));
		challengeProgrammingMessagedto.setMyMessage3((String)map.get("my_message3"));
		
		return challengeProgrammingMessagedto;
	}
}
