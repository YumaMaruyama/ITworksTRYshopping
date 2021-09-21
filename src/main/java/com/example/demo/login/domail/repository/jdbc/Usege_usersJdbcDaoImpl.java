package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.repository.Usege_usersDao;

@Repository
public class Usege_usersJdbcDaoImpl implements Usege_usersDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(Usege_usersDTO usegedto) {

		int result = jdbc.update(
				"insert into usege_users (id," + "user_id," + "birthday," + "address)" + "value(?,?,?,?)",
				usegedto.getId(), usegedto.getUser_id(), usegedto.getBirthday(), usegedto.getAddress());

		return result;
	}

	public String selectAddress(int select_id) {

		String address = jdbc.queryForObject("select usege_users.address from usege_users where user_id = ?",
				String.class, select_id);

		return address;

	}

	public List<UsersListDTO> selectMany() {
		List<Map<String, Object>> map = jdbc.queryForList("select * from usege_users where id != 1");

		List<UsersListDTO> usegeuserslist = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			UsersListDTO userslistdto = new UsersListDTO();
			userslistdto.setBirthday((Date) oneMap.get("birthday"));
			userslistdto.setAddress((String) oneMap.get("address"));

			usegeuserslist.add(userslistdto);
		}

		return usegeuserslist;
	}

	public UsersListDTO selectOne(int id) {
		Map<String, Object> map = jdbc.queryForMap("select * from usege_users where id != 1 and user_id = ?", id);
		UsersListDTO usegeuserslistdto = new UsersListDTO();
		usegeuserslistdto.setBirthday((Date) map.get("birthday"));
		usegeuserslistdto.setAddress((String) map.get("address"));

		return usegeuserslistdto;

	}

	public int deleteOne(int id) {
		int result = jdbc.update("delete from usege_users where user_id = ?", id);

		return result;
	}

	public Usege_usersDTO userInformationSelectOne(int selectId) {
		Map<String, Object> map = jdbc.queryForMap("select * from usege_users where user_id = ?", selectId);
		Usege_usersDTO usegeusersdto = new Usege_usersDTO();
		usegeusersdto.setBirthday((Date) map.get("birthday"));
		usegeusersdto.setAddress((String) map.get("address"));

		return usegeusersdto;
	}

	public int updateOne(Usege_usersDTO usegeusersdto) {
		int result = jdbc.update("update usege_users set address = ? where id = ?", usegeusersdto.getAddress(),
				usegeusersdto.getId());
		return result;
	}
}
