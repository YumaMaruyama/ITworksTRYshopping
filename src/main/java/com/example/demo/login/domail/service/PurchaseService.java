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

	public List<PcDataDTO> selectMany(int select_id) {
		return dao.selectMany(select_id);
	}


	public Date selectPurchaseDate() {
		return dao.selectPurchaseDate();
	}

}