package com.example.demo.login.domail.repository;

import java.util.Date;

import com.example.demo.login.domail.model.CancelDTO;

public interface CancelDao {

	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName);
	
	public int insertOneCancelCheck(CancelDTO canceldto,int userId, int purchaseId, int productId,String title, String content,int bankNumber, int storeName);
	
	public int deleteOne(int purchaseId);
	
	public int cancelCheckUpdate(int purchaseId,String DeliveryAddress);
	
	public CancelDTO selectCancelCheck(int purchaseId);
	
	public int deliveryDateUpdate(int purchaseId,Date deliveryDate);
	
	public CancelDTO selectDerivaryDate(int purchaseId);
	
	public CancelDTO deliveryDateCheck(CancelDTO canceldto,int purchaseId);
	
	public int selectCancelCheck(int purchaseId,int userId);
	
	public CancelDTO cancelCheckSelect(int maxId);
	
	public int deliveryAddressSelect(int purchaseId);
	
	public String deriveredCheckSelect(int purchaseId);
	
	public CancelDTO selectOne(int purchaseId);
	
	public int cancelCompletedUpdate(int purchaseId,int pointUse,int pointRepayment);
}
