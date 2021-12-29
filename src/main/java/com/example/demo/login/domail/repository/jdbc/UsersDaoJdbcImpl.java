package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.repository.UsersDao;

@Repository
public class UsersDaoJdbcImpl implements UsersDao {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired // SpringにJdbcTemplateが入っており、Bean定義がされている
				// このアノテーションをつけるだけで、このクラスのメソッドを使ってSQLを実行できる
	JdbcTemplate jdbc;

	public int insertOne(UsersDTO usersdto) {

		String password = passwordEncoder.encode(usersdto.getPassword());

		int rowNumber = jdbc.update(
				"insert into users (id," + "user_id," + "password," + "user_name," + "role)" + "value(?,?,?,?,?)",
				usersdto.getId(), usersdto.getUser_id(), password, usersdto.getUser_name(), usersdto.getRole());

		return rowNumber;

	}

	public String selectId(String user_id) {

		String selectUser_id = jdbc.queryForObject("select users.id from users where user_id = ?", String.class,
				user_id);

		return selectUser_id;
	}

	public UsersDTO getUser_name(String personId) {
		System.out.println("dao到達");
		Map<String, Object> map = jdbc.queryForMap("select * from users where user_id = ?", personId);

		UsersDTO usersdto = new UsersDTO();
		usersdto.setUser_name((String) map.get("user_name"));
		usersdto.setRole((String) map.get("role"));
		System.out.println("dto" + usersdto.getUser_name());
		return usersdto;
	}

	public int select_id(String getName) {
		int id = jdbc.queryForObject("select users.id from users where user_id = ?", Integer.class, getName);

		return id;
	}

	public String check(String user_id) throws EmptyResultDataAccessException {

		String result = "0";
		try {
			String selectResult = jdbc.queryForObject("select users.user_id from users where user_id = ?", String.class,
					user_id);
			result = "1";
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<UsersListDTO> selectMany(String adminCheck) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from users where role != ?", adminCheck);

		List<UsersListDTO> usersList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			UsersListDTO userslistdto = new UsersListDTO();
			userslistdto.setId((int) oneMap.get("id"));
			userslistdto.setUserId((String) oneMap.get("user_id"));
			userslistdto.setUserName((String) oneMap.get("user_name"));

			usersList.add(userslistdto);
		}

		return usersList;
	}

	public UsersListDTO selectOne(int id) {
		String adminCheck = "admin";
		Map<String, Object> map = jdbc.queryForMap("select * from users where id = ? and role != ?", id, adminCheck);

		UsersListDTO userslistdto = new UsersListDTO();
		userslistdto.setUserId((String) map.get("user_id"));
		userslistdto.setUserName((String) map.get("user_name"));

		return userslistdto;
	}

	public int deleteOne(int id) {
		int result = jdbc.update("delete from users where id = ?", id);

		return result;
	}

	public UsersDTO userInformationSelectOne(int selectId) {
		Map<String, Object> map = jdbc.queryForMap("select * from users where id = ?", selectId);

		UsersDTO usersdto = new UsersDTO();
		usersdto.setId((int) map.get("id"));
		usersdto.setUser_id((String) map.get("user_id"));
		usersdto.setUser_name((String) map.get("user_name"));

		return usersdto;
	}

	public int updateOne(UsersDTO usersdto) {
		int result = jdbc.update("update users set user_name = ? where id = ?", usersdto.getUser_name(),
				usersdto.getId());

		return result;
	}

	public List<UsersDTO> selectMany() {
		List<Map<String, Object>> map = jdbc.queryForList("select * from users");

		List<UsersDTO> usersIdList = new ArrayList<>();

		for (Map<String, Object> oneMap : map) {
			UsersDTO usersdto = new UsersDTO();
			usersdto.setId((int) oneMap.get("id"));

			usersIdList.add(usersdto);
		}

		return usersIdList;
	}

	public String selectRole(int userId) {
		String userRole = jdbc.queryForObject("select users.role from users where id = ?", String.class, userId);

		return userRole;
	}
	
	public String mailAddressSelectOne(int userId) {
		String mailAddress = jdbc.queryForObject("select users.user_id from users where id = ?",String.class,userId);
		return mailAddress;
	}
	
	public String userNameSelectOne(int userId) {
		String userName = jdbc.queryForObject("select users.user_name from users where id = ?",String.class,userId);
		
		return userName;
	}

}
