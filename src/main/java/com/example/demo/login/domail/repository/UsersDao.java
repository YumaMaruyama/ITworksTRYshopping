package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;

public interface UsersDao {

	public int insertOne(UsersDTO usersdto);

	public UsersDTO getUser_name(String personId);

	public int select_id(String getName);

	public String selectId(String user_id);

	public String check(String user_id);

	public List<UsersListDTO> selectMany(String adminCheck);

	public UsersListDTO selectOne(int id);

	public int deleteOne(int id);

	public UsersDTO userInformationSelectOne(int selectId);

	public int updateOne(UsersDTO usersdto);

	public List<UsersDTO> selectMany();

	public String selectRole(int userId);
	
	public String mailAddressSelectOne(int userId);
	
	public String userNameSelectOne(int userId);

}
