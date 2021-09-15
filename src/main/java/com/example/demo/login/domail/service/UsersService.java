package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.UsersDTO;
import com.example.demo.login.domail.model.UsersListDTO;
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

	public String selectId(String user_id) {
		return dao.selectId(user_id);
	}

	public int select_id(String getName) {
		return dao.select_id(getName);
	}

	public UsersDTO getUser_name(String personId) {
		System.out.println("service");
		return dao.getUser_name(personId);
	}

	public String check(String user_id) {
		return dao.check(user_id);
	}
	
	public List<UsersListDTO> selectMany(String adminCheck) {
		return dao.selectMany(adminCheck);
	}
	
	public UsersListDTO selectOne(int id) {
		return dao.selectOne(id);
	}
	
	public int deleteOne(int id) {
		int result = dao.deleteOne(id);
		if(result > 0) {
			System.out.println("deleteOne(usersService)成功");
		}
		
		return result;
	}
	
	public UsersDTO userInformationSelectOne(int selectId) {
		return dao.userInformationSelectOne(selectId);
		
	}
	
	public int updateOne(UsersDTO usersDTO) {
		return dao.updateOne(usersDTO);
	}
	
	public List<UsersDTO> selectMany() {
		return dao.selectMany();
	}
	
	public String selectRole(int userId) {
		return dao.selectRole(userId);
	}
	
	
}
