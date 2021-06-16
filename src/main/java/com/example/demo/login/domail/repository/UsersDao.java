package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.UsersDTO;


public interface UsersDao {

	public int insertOne(UsersDTO usersdto);

	public UsersDTO getUser_name(String personId);
}
