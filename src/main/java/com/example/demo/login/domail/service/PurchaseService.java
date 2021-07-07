package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.repository.PurchaseDao;

@Service
public class PurchaseService {

	@Autowired
	PurchaseDao dao;

	public int insert(int purchaseId,int purchaseCount,int totalPrice,int select_id) {
		return dao.insert(purchaseId,purchaseCount,totalPrice,select_id);
	}
}
