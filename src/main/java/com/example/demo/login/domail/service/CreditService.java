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

	public int clearingInsertOne(CreditDTO creditdto,int select_id,int totalPrice) {
		int result =dao.clearingInsertOne(creditdto,select_id,totalPrice);

		if(result > 0) {
			System.out.println("insert成功");
		}

		return result;
	}

	public CreditDTO clearingSelectOne(int getId) {

		 return  dao.clearingSelectOne(getId);
	}

	public int selectMaxId() {

		int selectMaxId = dao.selectMaxId();

		if(selectMaxId > 0) {
			System.out.println("select成功");
		}

		return selectMaxId;
	}

}
