package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.CancelDTO;

public interface CancelDao {

	public int insertOne(CancelDTO canceldto,int userId, int purchaseId, int product_id,String title, String content,int bankNumber, int storeName);
	
	public int insertOneCancelCheck(CancelDTO canceldto,int userId, int purchaseId, int productId,String title, String content,int bankNumber, int storeName);
}
