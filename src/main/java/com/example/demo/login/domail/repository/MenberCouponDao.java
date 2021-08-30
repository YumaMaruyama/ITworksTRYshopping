package com.example.demo.login.domail.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.MenberCouponDTO;

public interface MenberCouponDao {

	public int menberCouponInsert(MenberCouponDTO menbercoupondto,HttpSession session);
	
	public  List<MenberCouponDTO> selectMany(int rankNumber);
}
