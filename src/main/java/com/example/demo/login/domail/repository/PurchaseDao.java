package com.example.demo.login.domail.repository;

import java.util.Date;
import java.util.List;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;

public interface PurchaseDao {

	public int insert(PurchaseDTO purchasedto,int purchaseId,int purchaseCount,int select_id,int purchaseCreditId,int customId);

	public List<PcDataDTO> selectMany(int select_id,int purchaseNumber);

	public Date selectPurchaseDate(int purchaseNumber);

	public int selectPurchaseNumber(int select_id);

	public List<PurchaseDTO> selectHistory(int select_id);
	
	public PurchaseDTO selectOne(int id);
	
	public int selectPurchaseIdOne();
	
	public PurchaseDTO reviewSelectHistory(int selectId,int purchaseId);
	
	public int deleteOne(int purchaseId);

	public int insertOneCancelCheck(int purchaseId);
	
	public int cancelCheckUpdate(int purchaseId);
	
	public int cancelCheckUpdateNext(int purchaseId);

}
