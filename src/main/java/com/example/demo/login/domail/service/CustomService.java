package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CustomDTO;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.CustomDao;

@Service
public class CustomService {

	@Autowired
	CustomDao dao;

	public int UpdateOne(int id, int select_id, String memory, String hardDisc, String cpu, int customPrice) {
		return dao.UpdateOne(id, select_id, memory, hardDisc, cpu, customPrice);
	}

	public PcDetailDataDTO selectOne(int id, int select_id) {
		return dao.selectOne(id, select_id);
	}

	public CustomDTO selectCustomProduct_id(int id, int select_id) {
		return dao.selectCustomProduct_id(id, select_id);
	}

	public int insertCustomData(int id, int select_id, String defaultMemory, String defaultHardDisc,
			String defaultCpu,int customPrice) {
		return dao.insertCustomData(id, select_id, defaultMemory, defaultHardDisc, defaultCpu,customPrice);
	}

	public int deleteCustomOne(int id,int getId) {
		return dao.deleteCustomOne(id,getId);
	}
	public int selectCustomId(int productId,int select_id) {
		return dao.selectCustomId(productId,select_id);
	}

	public PurchaseDTO selectMany(int customId) {
		return dao.selectMany(customId);
	}
	
	public int purchaseCheckUpdate(int select_id,int product_id) {
		int result = dao.purchaseCheckUpdate(select_id,product_id);
		if(result > 0) {
			System.out.println("purchaseCheckUpdate成功");
		}
		
		return result;
	}
	
	public int selectPurchaseCheck(int select_id,int product_id,String nullCheck) {
		return dao.selectPurchaseCheck(select_id,product_id,nullCheck);
	}
	
	public int pruchaseIdInsertOne(int purchaseId) {
		return dao.pruchaseIdInsertOne(purchaseId);
	}
	
	public int pruchaseIdUpdate(int purchaseId,int productId,int userId) {
		return dao.purchaseIdUpdate(purchaseId,productId,userId);
		
	}
}
