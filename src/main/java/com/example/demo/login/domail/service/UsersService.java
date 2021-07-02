package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.repository.UsersDao;

@Service
public class UsersService {

	@Autowired
	UsersDao dao;

	public int insertOne(UsersDTO usersdto) {

		int rowNumber =  dao.insertOne(usersdto);

		if(rowNumber > 0) {
		System.out.println("insert成功");
		}

		return rowNumber;
	}

	public int select_id(String getName) {
		return dao.select_id(getName);
	}

	public UsersDTO getUser_name(String personId) {
		System.out.println("service");
		return dao.getUser_name(personId);
	}
}
