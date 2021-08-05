package com.example.demo.login.domail.repository;

import java.util.Date;

import com.example.demo.login.domail.model.CancelDTO;

public interface CancelDao {

	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName);
	
	public int insertOneCancelCheck(CancelDTO canceldto,int userId, int purchaseId, int productId,String title, String content,int bankNumber, int storeName);
	
	public int deleteOne(int purchaseId);
	
	public int cancelCheckUpdate(int purchaseId);
	
	public CancelDTO selectCancelCheck(int purchaseId);
	
	public int deliveryDateUpdate(CancelDTO canceldto,Date deliveryDate);
}
