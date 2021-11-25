package com.example.demo.login.domail.repository;

import java.util.Date;

import com.example.demo.login.domail.model.GachaDTO;

public interface GachaDao {

	public int gachaTurnInsertOne(GachaDTO gachadto,int userId,Date nowDate);
	
	public Date gachaTurnedCheck(int userId);
}
