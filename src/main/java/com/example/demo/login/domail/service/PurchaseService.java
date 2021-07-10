package com.example.demo.login.domail.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.PurchaseDao;

@Service
public class PurchaseService {

	@Autowired
	PurchaseDao dao;

	public int insert(PurchaseDTO purchasedto,int purchaseId,int purchaseCount,int select_id,int purchaseCreditId) {
		int result = dao.insert(purchasedto,purchaseId,purchaseCount,select_id,purchaseCreditId);

		if(result > 0) {
			System.out.println("insert成功");
		}

		return result;

	}

	public List<PcDataDTO> selectMany(int select_id,int purchaseNumber) {
		return dao.selectMany(select_id,purchaseNumber);
	}


	public Date selectPurchaseDate(int purchaseNumber) {
		return dao.selectPurchaseDate(purchaseNumber);

	}

	public int selectPurchaseNumber(int select_id) {
		int result = dao.selectPurchaseNumber(select_id);

		if(result > 0) {
			System.out.println("selectPurchaseNumber insert成功");
		}

		return result;
	}

}