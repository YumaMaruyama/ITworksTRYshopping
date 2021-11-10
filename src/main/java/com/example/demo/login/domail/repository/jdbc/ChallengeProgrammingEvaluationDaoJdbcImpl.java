package com.example.demo.login.domail.repository.jdbc;

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
}
