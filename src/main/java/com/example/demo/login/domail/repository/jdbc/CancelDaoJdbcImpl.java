package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.repository.CancelDao;

@Repository
public class CancelDaoJdbcImpl implements CancelDao {

	@Autowired
	JdbcTemplate jdbc;
}
