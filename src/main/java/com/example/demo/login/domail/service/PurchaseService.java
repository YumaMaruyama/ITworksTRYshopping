package com.example.demo.login.domail.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.PurchaseDao;

@Service
public class PurchaseService {

	@Autowired
	PurchaseDao dao;

	public int insert(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id, int purchaseCreditId,
			int customId, int couponId, int point, int pointminusTotalPrice) {
		return dao.insert(purchasedto, purchaseId, purchaseCount, select_id, purchaseCreditId, customId, couponId,
				point, pointminusTotalPrice);
	}

	public int insertMenberCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int couponId, int point, int pointminusTotalPrice) {
		return dao.insertMenberCoupon(purchasedto, purchaseId, purchaseCount, select_id, purchaseCreditId, customId,
				couponId, point, pointminusTotalPrice);
	}

	public List<PcDataDTO> selectMany(int select_id, int purchaseNumber) {
		return dao.selectMany(select_id, purchaseNumber);
	}

	public Date selectPurchaseDate(int purchaseNumber) {
		return dao.selectPurchaseDate(purchaseNumber);

	}

	public int selectCreditId(int select_id) {
		return dao.selectCreditId(select_id);
	}

	public List<PurchaseDTO> selectHistory(int select_id) {
		return dao.selectHistory(select_id);
	}

	public PurchaseDTO selectOne(int id) {
		return dao.selectOne(id);
	}

	public int selectMaxPurchaseId() {
		return dao.selectMaxPurchaseId();
	}

	public PurchaseDTO reviewSelectHistory(int selectId, int purchaseId) {
		return dao.reviewSelectHistory(selectId, purchaseId);
	}

	public int deleteOne(int purchaseId) {
		return dao.deleteOne(purchaseId);
	}

	public int insertOneCancelCheck(int purchaseId) {
		return dao.insertOneCancelCheck(purchaseId);
	}

	public int cancelCheckUpdate(int purchaseId) {
		return dao.cancelCheckUpdate(purchaseId);
	}

	public int cancelCheckUpdateNext(int purchaseId) {
		return dao.cancelCheckUpdateNext(purchaseId);
	}

	public int insertNotCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int point, int pointminusTotalPrice) {
		return dao.insertNotCoupon(purchasedto, purchaseId, purchaseCount, select_id, purchaseCreditId, customId, point,
				pointminusTotalPrice);
	}

	public List<PurchaseDTO> selectPoint(int userId) {
		return dao.selectPoint(userId);
	}
	
	public List<PurchaseDTO> deliverySelect() {
		return dao.deliverySelect();
	}
	
	public int deliveryProcedureCheckInsertOne(int purchaseId) {
		return dao.deliveryProcedureCheckInsertOne(purchaseId);
	}
	

}