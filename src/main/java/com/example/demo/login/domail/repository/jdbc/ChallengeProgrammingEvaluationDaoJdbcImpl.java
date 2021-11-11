package com.example.demo.login.domail.repository.jdbc;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.ChallengeProgrammingEvaluationDTO;
import com.example.demo.login.domail.model.LessonEvaluationForm;
import com.example.demo.login.domail.repository.ChallengeProgrammingEvaluationDao;

@Repository
public class ChallengeProgrammingEvaluationDaoJdbcImpl implements ChallengeProgrammingEvaluationDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int evaluationInsertOne(int userId,int productId,ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTO,LessonEvaluationForm form) {
		
		int result = jdbc.update("insert into challenge_programming_evaluation (id,"
				+ " teacher_id,"
				+ " user_id,"
				+ " rate,"
				+ " evaluation_detail)"
				+ " value(?,?,?,?,?)",challengeprogrammingevaluationDTO.getId(),productId,userId,form.getRating(),form.getEvaluatonDetail());
				
		return result;
	}
	
	public ChallengeProgrammingEvaluationDTO evaluationSelectMany() {
		Map<String,Object> map = jdbc.queryForMap("select challenge_programming_evaluation.id,challenge_programming_evaluation.rate,challenge_programming_evaluation.evaluation_detail,challenge_programming_evaluation.registration_date,challenge_programming.my_name,users.user_name from challenge_programming_evaluation join challenge_programming on challenge_programming.id = challenge_programming_evaluation.teacher_id join users on users.id = challenge_programming_evaluation.user_id");
		
		ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTO = new ChallengeProgrammingEvaluationDTO();
		challengeprogrammingevaluationDTO.setId((int)map.get("id"));
		challengeprogrammingevaluationDTO.setTeacherName((String)map.get("my_name"));
		challengeprogrammingevaluationDTO.setUserName((String)map.get("user_name"));
		challengeprogrammingevaluationDTO.setRate((int)map.get("rate"));
		challengeprogrammingevaluationDTO.setEvaluationDetail((String)map.get("evaluation_detail"));
		challengeprogrammingevaluationDTO.setRegistrationDate((Date)map.get("registration_date"));
		
		return challengeprogrammingevaluationDTO;
	}
}
