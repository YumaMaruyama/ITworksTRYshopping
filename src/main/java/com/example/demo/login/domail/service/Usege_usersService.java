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
		return dao.insertOne(usegedto);
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
	
	public int deleteOne(int id) {
		return dao.deleteOne(id);
	}
	
	public Usege_usersDTO userInformationSelectOne(int selectId) {
		return dao.userInformationSelectOne(selectId);
	}
	
	public int updateOne(Usege_usersDTO usegeusersdto) {
		return dao.updateOne(usegeusersdto);
	}
 }
