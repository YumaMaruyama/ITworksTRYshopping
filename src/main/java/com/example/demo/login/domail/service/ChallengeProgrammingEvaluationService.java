package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.ChallengeProgrammingEvaluationDTO;
import com.example.demo.login.domail.model.LessonEvaluationForm;
import com.example.demo.login.domail.repository.ChallengeProgrammingEvaluationDao;

@Service
public class ChallengeProgrammingEvaluationService {

	@Autowired
	ChallengeProgrammingEvaluationDao dao;
	
	public int evaluationInsertOne(int userId,int productId,ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTO,LessonEvaluationForm form) {
		return dao.evaluationInsertOne(userId,productId,challengeprogrammingevaluationDTO,form);
	}
	
	public List<ChallengeProgrammingEvaluationDTO> evaluationSelectMany(int productId) {
		return dao.evaluationSelectMany(productId);
	}
}
