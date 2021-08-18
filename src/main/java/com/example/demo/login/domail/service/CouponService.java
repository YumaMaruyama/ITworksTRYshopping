package com.example.demo.login.domail.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CouponDTO;
import com.example.demo.login.domail.repository.CouponDao;

@Service
public class CouponService {

	@Autowired
	CouponDao dao;
	
	public int couponInsert(CouponDTO coupondto,HttpSession session) {
		return dao.couponInsert(coupondto,session);
	}
	
	public List<CouponDTO> selectMany(int beforeUseCouponId) {
		return dao.selectMany(beforeUseCouponId);
	}
	
	public CouponDTO selectOne(int couponId) {
		return dao.selectOne(couponId);
	}
	
	public List<Integer> selectIdMany() {
		return dao.selectIdMany();
	}
	
}
