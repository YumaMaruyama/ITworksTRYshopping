package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.repository.Usege_usersDao;

@Repository
public class Usege_usersJdbcDaoImpl implements Usege_usersDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(Usege_usersDTO usegedto) {

		int result = jdbc.update("insert into usege_users (id,"
				+ "user_id,"
				+ "birthday,"
				+ "address)"
				+ "value(?,?,?,?)",usegedto.getId(),usegedto.getUser_id(),usegedto.getBirthday(),usegedto.getAddress());

		return result;
	}
}
