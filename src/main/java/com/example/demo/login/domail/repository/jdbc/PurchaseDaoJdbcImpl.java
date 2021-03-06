package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.SalesManagementForm;
import com.example.demo.login.domail.repository.PurchaseDao;

@Repository
public class PurchaseDaoJdbcImpl implements PurchaseDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insert(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id, int purchaseCreditId,
			int customId, int couponId, int point, int pointminusTotalPrice) {

		int result = jdbc.update("insert into purchase (id," + " user_id," + " product_id," + " product_count,"

				+ " credit_id," + " custom_id," + " menber_coupon_check," + " coupon_id," + " point," + " point_use," + " delivery_check)"
				+ " value(?,?,?,?,?,?,?,?,?,?,?)", purchasedto.getId(), select_id, purchaseId, purchaseCount,
				purchaseCreditId, customId, "クーポン使用", couponId, point, pointminusTotalPrice,"発送前");

		return result;

	}

	public int insertMenberCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int couponId, int point, int pointminusTotalPrice) {
		int result = jdbc.update("insert into purchase (id," + " user_id," + " product_id," + " product_count,"

				+ " credit_id," + " custom_id," + " menber_coupon_check," + " coupon_id," + " point," + " point_use," + " delivery_check)"
				+ " value(?,?,?,?,?,?,?,?,?,?,?)", purchasedto.getId(), select_id, purchaseId, purchaseCount,
				purchaseCreditId, customId, "会員クーポン使用", couponId, point, pointminusTotalPrice,"発送前");

		return result;
	}

	public List<PcDataDTO> selectMany(int select_id, int purchaseNumber) {
		List<Map<String, Object>> idList = jdbc.queryForList(
				"select purchase.product_id from purchase where user_id = ? and credit_id = ?", select_id,
				purchaseNumber);

		List<Integer> productIdList = new ArrayList<>();
		for (Map<String, Object> map : idList) {
			int productIdOne = ((int) map.get("product_id"));
			productIdList.add(productIdOne);
		}

		
		List<Map<String, Object>> user_productIdList = new ArrayList<>();
		for (int i = 0; 1 > i; i++) {
			List<Map<String, Object>> purchaseList = jdbc.queryForList(
					"select pcdata.id,pcdata.company,pcdata.os,pcdata.pc_name,pcdata.pc_size,pcdata.price,pcdata.detail,pcdata.product_stock,pcdata.pcimg,pcdata.pcimg2,pcdata.pcimg3,purchase.credit_id,purchase.product_count from pcdata JOIN purchase ON pcdata.id = purchase.product_id and purchase.credit_id = ?",
					purchaseNumber);
			user_productIdList.addAll(purchaseList);

		}

		List<PcDataDTO> purchasDataList = new ArrayList<>();
		System.out.println("productIdList" + user_productIdList);
		for (Map<String, Object> map : user_productIdList) {
			System.out.println("map" + map);
			PcDataDTO pcdatadto = new PcDataDTO();

			pcdatadto.setId((int) map.get("id"));
			pcdatadto.setCompany((String) map.get("company"));
			pcdatadto.setOs((String) map.get("os"));
			pcdatadto.setPc_name((String) map.get("pc_name"));
			pcdatadto.setPc_size((int) map.get("pc_size"));
			pcdatadto.setPrice((int) map.get("price"));
			pcdatadto.setDetail((String) map.get("detail"));
			pcdatadto.setProduct_stock((int) map.get("product_stock"));
			pcdatadto.setPcImg((String) map.get("pcImg"));
			pcdatadto.setPcImg2((String) map.get("pcImg2"));
			pcdatadto.setPcImg3((String) map.get("pcImg3"));
			pcdatadto.setCreditId((int) map.get("credit_id"));
			pcdatadto.setProduct_count((int) map.get("product_count"));

			purchasDataList.add(pcdatadto);

		}

		return purchasDataList;

	}

	public Date selectPurchaseDate(int purchaseNumber) {
		Date purchaseDate = jdbc.queryForObject(
				"select purchase.purchase_date from purchase where purchase_date = (select max(purchase_date) from purchase) limit 1",
				Date.class);

		return purchaseDate;
	}

	public int selectCreditId(int select_id) {
		int purchaseNumber = jdbc.queryForObject(
				"select purchase.credit_id from purchase where purchase.credit_id = (select max(credit_id) from purchase) and user_id = ? limit 1",
				Integer.class, select_id);

		return purchaseNumber;
	}

	public List<PurchaseDTO> selectHistory(int select_id) {

		List<Map<String, Object>> map = jdbc.queryForList(
				"select purchase.id,purchase.product_id,purchase.purchase_date,purchase.product_count,purchase.cancel_check,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,pcdata.pc_name,pcdata.price,pcdata.pcImg,cart.purchase_check as cartPurchaseCheck from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check where purchase.user_id = ? ",
				select_id);

		List<PurchaseDTO> purchaseList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			PurchaseDTO purchasedto = new PurchaseDTO();

			purchasedto.setId((int) oneMap.get("product_id"));
			purchasedto.setPurchaseId((int) oneMap.get("id"));
			purchasedto.setPurchase_date((Date) oneMap.get("purchase_date"));
			purchasedto.setCancelCheck((String) oneMap.get("cancel_check"));
			purchasedto.setPointUse((int) oneMap.get("point_use"));
			purchasedto.setCouponId((int) oneMap.get("coupon_id"));
			purchasedto.setPcName((String) oneMap.get("pc_name"));
			purchasedto.setPrice((int) oneMap.get("price"));
			purchasedto.setPcImg((String) oneMap.get("pcImg"));
			purchasedto.setProduct_count((int) oneMap.get("product_count"));
			purchasedto.setPurchaseCheck((int) oneMap.get("cartPurchaseCheck"));
			purchasedto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));

			purchaseList.add(purchasedto);
		}

		return purchaseList;
	}

	public PurchaseDTO selectOne(int id) {
		Map<String, Object> map = jdbc.queryForMap(
				"select purchase.id,purchase.product_id,purchase.purchase_date,purchase.product_count,pcdata.pc_name,pcdata.price from purchase join pcdata on purchase.product_id = pcdata.id where purchase.id = ? ",
				id);

		PurchaseDTO purchasedto = new PurchaseDTO();
		purchasedto.setId((int) map.get("product_id"));
		purchasedto.setPurchaseId((int) map.get("id"));
		purchasedto.setPurchase_date((Date) map.get("purchase_date"));
		purchasedto.setPcName((String) map.get("pc_name"));
		purchasedto.setPrice((int) map.get("price"));
		purchasedto.setProduct_count((int) map.get("product_count"));

		return purchasedto;

	}

	public int selectMaxPurchaseId() {
		int id = jdbc.queryForObject("select max(id) from purchase", Integer.class);

		return id;

	}

	public PurchaseDTO reviewSelectHistory(int selectId, int purchaseId) {
		try {
			Map<String, Object> map = jdbc.queryForMap(
					"select purchase.id,purchase.product_id,purchase.purchase_date,purchase.product_count,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,purchase.point,pcdata.id as pcDataId,pcdata.pc_name,pcdata.price,cart.purchase_check as cartPurchaseCheck from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check where purchase.id = ? ",
					purchaseId);

			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setId((int) map.get("id"));
			purchasedto.setProduct_id((int) map.get("product_id"));
			purchasedto.setPurchase_date((Date) map.get("purchase_date"));
			purchasedto.setPcDataId((int) map.get("pcDataId"));
			purchasedto.setPcName((String) map.get("pc_name"));
			purchasedto.setPrice((int) map.get("price"));
			purchasedto.setProduct_count((int) map.get("product_count"));
			purchasedto.setCouponId((int) map.get("coupon_id"));
			purchasedto.setMenberCouponCheck((String) map.get("menber_coupon_check"));
			purchasedto.setPointRepayment((int) map.get("point"));
			purchasedto.setPointUse((int) map.get("point_use"));
			purchasedto.setPurchaseCheck((int) map.get("cartPurchaseCheck"));
			return purchasedto;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setId(0);
			return purchasedto;
		}
	}
	
	public List<PurchaseDTO> productSalesSelectMany() {
		try {
			List<Map<String, Object>> map = jdbc.queryForList("select purchase.id,purchase.product_id,purchase.user_id,purchase.purchase_date,purchase.product_count,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,purchase.point,pcdata.id as pcDataId,pcdata.pc_name,pcdata.price,pcdata.cost,cart.purchase_check as cartPurchaseCheck,users.user_name from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check join users on users.id = purchase.user_id");
			
			List<PurchaseDTO> salesList = new ArrayList<>();
			
			for(Map<String,Object> oneMap : map) {
			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setId((int) oneMap.get("id"));
			purchasedto.setProduct_id((int) oneMap.get("product_id"));
			purchasedto.setUserName((String)oneMap.get("user_name"));
			purchasedto.setPurchase_date((Date) oneMap.get("purchase_date"));
			purchasedto.setPcDataId((int) oneMap.get("pcDataId"));
			purchasedto.setPcName((String) oneMap.get("pc_name"));
			purchasedto.setPrice((int) oneMap.get("price"));
			purchasedto.setCost((int)oneMap.get("cost"));
			purchasedto.setProduct_count((int) oneMap.get("product_count"));
			purchasedto.setCouponId((int) oneMap.get("coupon_id"));
			purchasedto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));
			purchasedto.setPointRepayment((int) oneMap.get("point"));
			purchasedto.setPointUse((int) oneMap.get("point_use"));
			purchasedto.setPurchaseCheck((int) oneMap.get("cartPurchaseCheck"));
			
			salesList.add(purchasedto);
			}
			return salesList;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			List<PurchaseDTO> salesList = new ArrayList<>();
			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setId(0);
			return salesList;
		}
	}
	
	public List<PurchaseDTO> productSalesSearchSelectMany(SalesManagementForm form) {
		System.out.println("test"+form);
		
		StringBuilder sql = new StringBuilder();
	 	sql.append("select purchase.id,purchase.product_id,purchase.user_id,purchase.purchase_date,purchase.product_count,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,purchase.point,pcdata.id as pcDataId,pcdata.pc_name,pcdata.price,pcdata.cost,cart.purchase_check as cartPurchaseCheck,users.user_name from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check join users on users.id = purchase.user_id where purchase.id >= 1");

	 	List<Object> list = new ArrayList<Object>();
	 	
	 	if((form.getPurchaseName() != null) && (!form.getPurchaseName().isEmpty())){
	 		sql.append(" and users.user_name like ?");
			list.add("%" + form.getPurchaseName() + "%");
		}
	 	
	 	if((form.getPurchaseDateFrom() != null) && (form.getPurchaseDateTo() != null)) {
			sql.append(" and purchase.purchase_date between ? and ?");
			list.add(form.getPurchaseDateFrom());
			list.add(form.getPurchaseDateTo());
		}else if((form.getPurchaseDateFrom() != null) && (form.getPurchaseDateTo() == null)) {
			sql.append(" and purchase.purchase_date >= ?");
			list.add(form.getPurchaseDateFrom());
		}else if((form.getPurchaseDateFrom() == null) && (form.getPurchaseDateTo() != null)) {
			sql.append(" and purchase.purchase_date <= ?");
			list.add(form.getPurchaseDateTo());
		}
		
	 	Object[] addList = list.toArray(new Object[list.size()]);
		String sqlNew = sql.toString();
		List<Map<String,Object>> rowNumber = jdbc.queryForList(sqlNew,addList);
		List<PurchaseDTO> salesList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : rowNumber) {
		PurchaseDTO purchasedto = new PurchaseDTO();
		purchasedto.setId((int) oneMap.get("id"));
		purchasedto.setProduct_id((int) oneMap.get("product_id"));
		purchasedto.setUserName((String)oneMap.get("user_name"));
		purchasedto.setPurchase_date((Date) oneMap.get("purchase_date"));
		purchasedto.setPcDataId((int) oneMap.get("pcDataId"));
		purchasedto.setPcName((String) oneMap.get("pc_name"));
		purchasedto.setPrice((int) oneMap.get("price"));
		purchasedto.setCost((int)oneMap.get("cost"));
		purchasedto.setProduct_count((int) oneMap.get("product_count"));
		purchasedto.setCouponId((int) oneMap.get("coupon_id"));
		purchasedto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));
		purchasedto.setPointRepayment((int) oneMap.get("point"));
		purchasedto.setPointUse((int) oneMap.get("point_use"));
		purchasedto.setPurchaseCheck((int) oneMap.get("cartPurchaseCheck"));
		
		salesList.add(purchasedto);
		}
		return salesList;
	}

	public int deleteOne(int purchaseId) {
		int result = jdbc.update("delete from purchase where id = ?", purchaseId);

		return result;
	}

	public int insertOneCancelCheck(int purchaseId) {
		int result = jdbc.update("update purchase set cancel_check = 'キャンセル取引中' where id = ?", purchaseId);

		return result;
	}

	public int cancelCheckUpdate(int purchaseId) {
		int result = jdbc.update("update purchase set cancel_check = null where id = ?", purchaseId);
		return result;
	}

	public int cancelCheckUpdateNext(int purchaseId) {
		int result = jdbc.update("update purchase set cancel_check = '返品商品確認待ち' where id = ?", purchaseId);
		return result;
	}

	public int insertNotCoupon(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id,
			int purchaseCreditId, int customId, int point, int pointminusTotalPrice) {
		int result = jdbc.update(
				"insert into purchase (id," + " user_id," + " product_id," + " product_count," + " credit_id,"
						+ " custom_id," + " menber_coupon_check," + " coupon_id," + " point," + " point_use," + " delivery_check)"
						+ " value(?,?,?,?,?,?,?,?,?,?,?)",
				purchasedto.getId(), select_id, purchaseId, purchaseCount, purchaseCreditId, customId, "クーポン不使用", -1,
				point, pointminusTotalPrice,"発送前");

		return result;
	}

	public List<PurchaseDTO> selectPoint(int userId) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from purchase where user_id = ?", userId);

		List<PurchaseDTO> purchasePointList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			PurchaseDTO purchasedto = new PurchaseDTO();
			purchasedto.setPoint((int) oneMap.get("point"));
			purchasedto.setPointUse((int) oneMap.get("point_use"));
			purchasePointList.add(purchasedto);
		}
		return purchasePointList;
	}
	
	public List<PurchaseDTO> deliverySelect() {
		List<Map<String, Object>> map = jdbc.queryForList(
				"select purchase.id,purchase.user_id,purchase.product_id,purchase.purchase_date,purchase.product_count,purchase.cancel_check,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,purchase.delivery_check,pcdata.pc_name,pcdata.price,pcdata.pcImg,cart.purchase_check as cartPurchaseCheck,users.user_name,usege_users.address from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check join users on users.id = purchase.user_id join usege_users on usege_users.user_id = purchase.user_id"
				);

		List<PurchaseDTO> purchaseList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			PurchaseDTO purchasedto = new PurchaseDTO();

			purchasedto.setId((int) oneMap.get("product_id"));
			purchasedto.setPurchaseId((int) oneMap.get("id"));
			purchasedto.setUser_id((int)oneMap.get("user_id"));
			purchasedto.setPurchase_date((Date) oneMap.get("purchase_date"));
			purchasedto.setCancelCheck((String) oneMap.get("cancel_check"));
			purchasedto.setPointUse((int) oneMap.get("point_use"));
			purchasedto.setCouponId((int) oneMap.get("coupon_id"));
			purchasedto.setPcName((String) oneMap.get("pc_name"));
			purchasedto.setPrice((int) oneMap.get("price"));
			purchasedto.setPcImg((String) oneMap.get("pcImg"));
			purchasedto.setProduct_count((int) oneMap.get("product_count"));
			purchasedto.setPurchaseCheck((int) oneMap.get("cartPurchaseCheck"));
			purchasedto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));
			purchasedto.setDeliveryCheck((String)oneMap.get("delivery_check"));
			purchasedto.setUserName((String)oneMap.get("user_name"));
			purchasedto.setAddress((String)oneMap.get("address"));

			purchaseList.add(purchasedto);
		}

		return purchaseList;
	}
	
	public int deliveryProcedureCheckInsertOne(int purchaseId) {
		int result = jdbc.update("update purchase set delivery_check = '発送済み' where id = ?",purchaseId);
		
		return result;
	}
	
	public List<PurchaseDTO> cancelCheckSelectMany() {
		List<Map<String, Object>> map = jdbc.queryForList(
				"select purchase.id,purchase.user_id,purchase.product_id,purchase.purchase_date,purchase.product_count,purchase.cancel_check,purchase.coupon_id,purchase.menber_coupon_check,purchase.point_use,purchase.delivery_check,pcdata.pc_name,pcdata.price,pcdata.cost,pcdata.pcImg,cart.purchase_check as cartPurchaseCheck,users.user_name,usege_users.address from purchase join pcdata on purchase.product_id = pcdata.id join cart on purchase.id = cart.purchase_check join users on users.id = purchase.user_id join usege_users on usege_users.user_id = purchase.user_id where purchase.cancel_check != 'null'"
				);

		List<PurchaseDTO> purchaseList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			PurchaseDTO purchasedto = new PurchaseDTO();

			purchasedto.setId((int) oneMap.get("product_id"));
			purchasedto.setPurchaseId((int) oneMap.get("id"));
			purchasedto.setUser_id((int)oneMap.get("user_id"));
			purchasedto.setPurchase_date((Date) oneMap.get("purchase_date"));
			purchasedto.setCancelCheck((String) oneMap.get("cancel_check"));
			purchasedto.setPointUse((int) oneMap.get("point_use"));
			purchasedto.setCouponId((int) oneMap.get("coupon_id"));
			purchasedto.setPcName((String) oneMap.get("pc_name"));
			purchasedto.setPrice((int) oneMap.get("price"));
			purchasedto.setCost((int)oneMap.get("cost"));
			purchasedto.setPcImg((String) oneMap.get("pcImg"));
			purchasedto.setProduct_count((int) oneMap.get("product_count"));
			purchasedto.setPurchaseCheck((int) oneMap.get("cartPurchaseCheck"));
			purchasedto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));
			purchasedto.setDeliveryCheck((String)oneMap.get("delivery_check"));
			purchasedto.setUserName((String)oneMap.get("user_name"));
			purchasedto.setAddress((String)oneMap.get("address"));
			

			purchaseList.add(purchasedto);
		}

		return purchaseList;
	}
	
	
	

}
