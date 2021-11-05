package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.LessonStartForm;
import com.example.demo.login.domail.repository.ChallengeProgrammingLessonStartPasswordDao;

@Service
public class ChallengeProgrammingLessonStartPassword {

	@Autowired
	ChallengeProgrammingLessonStartPasswordDao dao;
	
	public boolean lessonStartPasswordCheck(LessonStartForm form) {
		return dao.lessonStartPasswordCheck(form);
	}
}
