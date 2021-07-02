package com.example.demo.login.domail.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.repository.UsersDao;

@Repository
public class UsersDaoJdbcImpl implements UsersDao {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired //SpringにJdbcTemplateが入っており、Bean定義がされている このアノテーションをつけるだけで、このクラスのメソッドを使ってSQLを実行できる
	JdbcTemplate jdbc;

	public int insertOne(UsersDTO usersdto) {

		String password = passwordEncoder.encode(usersdto.getPassword());

		int rowNumber = jdbc.update("insert into users (id,"
				+ "user_id,"
				+ "password,"
				+ "user_name,"
				+ "role)"
				+ "value(?,?,?,?,?)",usersdto.getId(),usersdto.getUser_id(),password,usersdto.getUser_name(),usersdto.getRole());

	return rowNumber;

	}

	public UsersDTO getUser_name(String personId) {
		System.out.println("dao到達");
		Map<String,Object> map = jdbc.queryForMap("select * from users where user_id = ?",personId);

		UsersDTO usersdto = new UsersDTO();
		usersdto.setUser_name((String)map.get("user_name"));
		System.out.println("dto" + usersdto.getUser_name());
		return usersdto;
	}

	public int select_id(String getName) {
		int id = jdbc.queryForObject("select users.id from users where user_id = ?",Integer.class,getName);

		return id;
	}

}
