package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.ProductGoodDao;

@Service
public class ProductGoodService {

	@Autowired
	ProductGoodDao dao;
	
	public int insertOne(int userId,int productId) {
		return dao.insertOne(userId,productId);
	}
	
	public List<Integer> selectMany(int userId) {
		return dao.selectMany(userId);
	}
	
	public int goodCheck(int userId,int productId) {
		return dao.goodCheck(userId,productId);
	}
	
	public int deleteOne(int userId,int productId) {
		return dao.deleteOne(userId,productId);
	}
}
