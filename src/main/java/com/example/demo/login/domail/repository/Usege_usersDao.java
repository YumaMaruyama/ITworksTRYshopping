package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.Usege_usersDTO;

public interface Usege_usersDao {

	public int insertOne(Usege_usersDTO usegedto);

	public String selectAddress(int select_id);
}
