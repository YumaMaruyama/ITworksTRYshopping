package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.LessonEndForm;
import com.example.demo.login.domail.model.LessonStartForm;
import com.example.demo.login.domail.repository.ChallengeProgrammingLessonStartPasswordDao;

@Repository
public class ChallengeProgrammingLessonStartPasswordDaoJdbcImpl implements ChallengeProgrammingLessonStartPasswordDao {

	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean lessonStartPasswordCheck(LessonStartForm form) {
		
		boolean passwordCheck = false;
		
		try {
		String password = jdbc.queryForObject("select challenge_programming_lesson_start_password.password from challenge_programming_lesson_start_password where password = ?",String.class,form.getPassword());
		passwordCheck = true;
		}catch(EmptyResultDataAccessException  | NullPointerException e) {
			e.printStackTrace();
	}
	
		return passwordCheck;
}
	
	public boolean lessonEndPasswordCheck(LessonEndForm form) {
		
		boolean passwordCheck = false;
		
		try {
			String password = jdbc.queryForObject("select challenge_programming_lesson_start_password.end_password from challenge_programming_lesson_start_password where end_password = ?", String.class,form.getPassword());
			passwordCheck = true;
		}catch(EmptyResultDataAccessException  | NullPointerException e) {
			e.printStackTrace();
		}
		return passwordCheck;
	}
	
	
}
