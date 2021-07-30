package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CancelDTO;
import com.example.demo.login.domail.repository.CancelDao;



@Service
public class CancelService {

	@Autowired
	CancelDao dao;
	
	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName) {
		return dao.insertOne(canceldto,userId,purchaseId,product_id,title,content,bankNumber,storeName);
	}
}
