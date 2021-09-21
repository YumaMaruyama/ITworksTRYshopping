package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CreditDTO;
import com.example.demo.login.domail.repository.CreditDao;

@Service
public class CreditService {

	@Autowired
	CreditDao dao;

	public int insertOne(CreditDTO creditdto, String getName) {

		return dao.insertOne(creditdto, getName);

	}

	public CreditDTO selectOne(String getName) {

		return dao.selectOne(getName);

	}

	public int clearingInsertOne(CreditDTO creditdto, int select_id, int totalPrice) {
		return dao.clearingInsertOne(creditdto, select_id, totalPrice);

	}

	public CreditDTO clearingSelectOne(int getId) {

		return dao.clearingSelectOne(getId);
	}

	public int selectMaxId() {

		return dao.selectMaxId();

	}

}
