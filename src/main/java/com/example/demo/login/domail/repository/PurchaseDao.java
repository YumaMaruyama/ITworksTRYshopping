package com.example.demo.login.domail.repository;

import java.util.Date;
import java.util.List;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.SalesManagementForm;

public interface PurchaseDao {

	public int insert(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id, int purchaseCreditId,
			int customId, int couponId, int point, int pointminusTotalPrice);

	public int insertMenberCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int couponId, int point, int pointminusTotalPrice);

	public List<PcDataDTO> selectMany(int select_id, int purchaseNumber);

	public Date selectPurchaseDate(int purchaseNumber);

	public int selectCreditId(int select_id);

	public List<PurchaseDTO> selectHistory(int select_id);

	public PurchaseDTO selectOne(int id);

	public int selectMaxPurchaseId();

	public PurchaseDTO reviewSelectHistory(int selectId, int purchaseId);

	public int deleteOne(int purchaseId);

	public int insertOneCancelCheck(int purchaseId);

	public int cancelCheckUpdate(int purchaseId);

	public int cancelCheckUpdateNext(int purchaseId);

	public int insertNotCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int point, int pointminusTotalPrice);

	public List<PurchaseDTO> selectPoint(int userId);

	public List<PurchaseDTO> deliverySelect();
	
	public int deliveryProcedureCheckInsertOne(int purchaseId);
	
	public List<PurchaseDTO> cancelCheckSelectMany();
	
	public List<PurchaseDTO> productSalesSelectMany();
	
	public List<PurchaseDTO> productSalesSearchSelectMany(SalesManagementForm form,String newPurchaseDateFrom,String newPurchaseDateTo);
}
