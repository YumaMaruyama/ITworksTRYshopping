package com.example.demo.login.domail.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.PointRateDao;

@Service
public class PointRateService {

	@Autowired
	PointRateDao dao;
	
	public int updateOne(HttpSession session) {
		return dao.updateOne(session);
	}
	
	public int selectOne(int id) {
		return dao.selectOne(id);
	}
}
