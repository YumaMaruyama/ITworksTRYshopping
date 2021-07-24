package com.example.demo.login.domail.repository;

import com.example.demo.login.domail.model.CustomDTO;
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;

public interface CustomDao {

	public int UpdateOne(int id,int select_id,String memory,String hardDisc,String cpu,int customPrice);

	public PcDetailDataDTO selectOne(int id,int select_id);

	public CustomDTO selectCustomProduct_id(int id,int select_id);

	public int insertCustomData(int id, int select_id, String defaultMemory, String defaultHardDisc,
			String defaultCpu,int customPrice);

	public int deleteCustomOne(int id,int getId);

	public int selectCustomId(int productId,int select_id);

	public PurchaseDTO selectMany(int customId);
	
	public int purchaseCheckUpdate(int select_id,int product_id);
	
	public int selectPurchaseCheck(int select_id,int product_id,String nullCheck);
	
	public int pruchaseIdInsertOne(int purchaseId);
	
	public int purchaseIdUpdate(int purchaseId,int productId,int userId);
}
