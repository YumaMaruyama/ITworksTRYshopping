package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.CreditDTO;

public interface CreditDao {


	public int insertOne(CreditDTO creditdto,String getName);

	public CreditDTO selectOne(String getName);

	public int clearingInsertOne(CreditDTO creditdto,int select_id,int totalPrice);

	public CreditDTO clearingSelectOne(int getId);

	public int selectMaxId();
}
