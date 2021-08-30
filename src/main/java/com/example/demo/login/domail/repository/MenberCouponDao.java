package com.example.demo.login.domail.repository;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.MenberCouponDTO;

public interface MenberCouponDao {

	public int menberCouponInsert(MenberCouponDTO menbercoupondto,HttpSession session);
	
	public int selectMany(int rankNumber);
}
