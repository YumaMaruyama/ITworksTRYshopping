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

}
