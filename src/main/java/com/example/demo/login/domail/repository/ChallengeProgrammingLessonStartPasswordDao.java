package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.LessonEndForm;
import com.example.demo.login.domail.model.LessonStartForm;

public interface ChallengeProgrammingLessonStartPasswordDao {
	
	public boolean lessonStartPasswordCheck(LessonStartForm form);
	
	public boolean lessonEndPasswordCheck(LessonEndForm form);

	
}
