package com.example.demo.login.domail.service;

import java.util.Date;
import java.util.List;

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
	
	public int cancelCheckUpdate(int purchaseId,String DeliveryAddress) {
		return dao.cancelCheckUpdate(purchaseId,DeliveryAddress);
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
	
	public int selectCancelCheck(int purchaseId,int userId) {
		return dao.selectCancelCheck(purchaseId,userId);
	}
	
	public CancelDTO cancelCheckSelect(int maxId) {
		return dao.cancelCheckSelect(maxId);
	}
	
	public int deliveryAddressSelect(int purchaseId) {
		return dao.deliveryAddressSelect(purchaseId);
	}
	
	public String deriveredCheckSelect(int purchaseId) {
		return dao.deriveredCheckSelect(purchaseId);
	}
	
	public CancelDTO selectOne(int purchaseId) {
		return dao.selectOne(purchaseId);
	}
	
	public int cancelCompletedUpdate(int purchaseId,int pointUse,int pointRepayment) {
		return dao.cancelCompletedUpdate(purchaseId,pointUse,pointRepayment);
	}
	
	public List<CancelDTO> selectPoint(int selectId) {
		return dao.selectPoint(selectId);
	}
}
