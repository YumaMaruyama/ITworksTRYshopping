package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UsersListDTO;

public interface Usege_usersDao {

	public int insertOne(Usege_usersDTO usegedto);

	public String selectAddress(int select_id);
	
	public List<UsersListDTO> selectMany();
	
	public UsersListDTO selectOne(int id);
	
	public int deleteOne(int id);
}
