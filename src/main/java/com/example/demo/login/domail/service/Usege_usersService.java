package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.Usege_usersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
import com.example.demo.login.domail.repository.Usege_usersDao;

@Service
public class Usege_usersService {

	@Autowired
	Usege_usersDao dao;

	public int insertOne(Usege_usersDTO usegedto) {
		int result = dao.insertOne(usegedto);

		if(result > 0) {
			System.out.println("insert成功");
		}
		return result;
	}

	public String selectAddress(int select_id) {
		return dao.selectAddress(select_id);
	}
	
	public List<UsersListDTO> selectMany() {
		return dao.selectMany();
	}
	
	public UsersListDTO selectOne(int id) {
		return dao.selectOne(id);
	}
 }
