package com.example.demo.login.domail.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.CouponDTO;

public interface CouponDao {

	public int couponInsert(CouponDTO coupondto,HttpSession session);
	
	public List<CouponDTO> selectMany();
	
	public CouponDTO selectOne(int couponId);
	
	public List<Integer> selectIdMany();
}
