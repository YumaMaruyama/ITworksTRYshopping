package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.ChallengeProgrammingEvaluationDTO;
import com.example.demo.login.domail.model.LessonEvaluationForm;

public interface ChallengeProgrammingEvaluationDao {

	public int evaluationInsertOne(int userId,int productId,ChallengeProgrammingEvaluationDTO challengeprogrammingevaluationDTO,LessonEvaluationForm form);
}
