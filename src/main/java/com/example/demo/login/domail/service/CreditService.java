package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.repository.CreditDao;

@Service
public class CreditService {

	@Autowired
	CreditDao dao;

	public int insertOne(CreditDTO creditdto,String getName) {

		int result = dao.insertOne(creditdto,getName);

		if(result > 0) {
			System.out.println("insert成功");
		}

		return result;
	}

	public CreditDTO selectOne(String getName) {

		return dao.selectOne(getName);

	}

	public int clearingInsertOne(CreditDTO creditdto,int select_id) {
		return dao.clearingInsertOne(creditdto,select_id);
	}
}
