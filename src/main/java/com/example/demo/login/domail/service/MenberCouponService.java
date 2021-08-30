package com.example.demo.login.domail.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.MenberCouponDTO;
import com.example.demo.login.domail.repository.MenberCouponDao;

@Service
public class MenberCouponService {

	@Autowired
	MenberCouponDao dao;

	public int menberCouponInsert(MenberCouponDTO menbercoupondto, HttpSession session) {
		return dao.menberCouponInsert(menbercoupondto, session);
	}
	
	public int selectMany(int rankNumber) {
		return dao.selectMany(rankNumber);
	}
}
