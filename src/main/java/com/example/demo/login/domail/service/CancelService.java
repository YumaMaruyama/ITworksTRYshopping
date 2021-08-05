package com.example.demo.login.domail.service;

import java.util.Date;

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
	
	public int insertOneCancelCheck(CancelDTO canceldto,int userId, int purchaseId, int productId,String title, String content,int bankNumber, int storeName) {
		return dao.insertOneCancelCheck(canceldto,userId,purchaseId,productId,title,content,bankNumber,storeName);
	}
	
	public int deleteOne(int purchaseId) {
		return dao.deleteOne(purchaseId);
	}
	
	public int cancelCheckUpdate(int purchaseId) {
		return dao.cancelCheckUpdate(purchaseId);
	}
	
	public CancelDTO selectCancelCheck(int purchaseId) {
		return dao.selectCancelCheck(purchaseId);
	}
	
	public int deliveryDateUpdate(int purchaseId,Date deliveryDate) {
		return dao.deliveryDateUpdate(purchaseId,deliveryDate);
	}
	
	public CancelDTO selectDerivaryDate(int purchaseId) {
		return dao.selectDerivaryDate(purchaseId);
	}
	
	public CancelDTO deliveryDateCheck(CancelDTO canceldto,int purchaseId) {
		return dao.deliveryDateCheck(canceldto,purchaseId);
	}
}
