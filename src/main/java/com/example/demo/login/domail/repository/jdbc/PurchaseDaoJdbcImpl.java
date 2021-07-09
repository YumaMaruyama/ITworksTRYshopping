package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.repository.PurchaseDao;

@Repository
public class PurchaseDaoJdbcImpl implements PurchaseDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insert(PurchaseDTO purchasedto, int purchaseId, int purchaseCount, int select_id, int purchaseCreditId) {

		int result = jdbc.update("insert into purchase (id,"
				+ " user_id,"
				+ " product_id,"
				+ " product_count,"
				+ " purchase_date,"
				+ " credit_id)"
				+ " value(?,?,?,?,?,?)", purchasedto.getId(), select_id, purchaseId, purchaseCount,
				purchasedto.getPurchase_date(), purchaseCreditId);

		return result;

	}

	public List<PcDataDTO> selectMany(int select_id) {
		List<Map<String, Object>> idList = jdbc
				.queryForList("select purchase.product_id from purchase where user_id = ?", select_id);

		List<Integer> productIdList = new ArrayList<>();
		for (Map<String, Object> map : idList) {
			int productIdOne = ((int) map.get("product_id"));
			productIdList.add(productIdOne);
		}

		int sumPrins = 0;
		List<Map<String, Object>> user_productIdList = new ArrayList<>();
		for (int i = 0; productIdList.size() > i; i++) {
			int productIdOne = productIdList.get(i);
			List<Map<String, Object>> purchaseList = jdbc.queryForList(
					"select pcdata.id,pcdata.company,pcdata.os,pcdata.pc_name,pcdata.pc_size,pcdata.price,pcdata.detail,pcdata.product_stock,pcdata.pcimg,pcdata.pcimg2,pcdata.pcimg3,purchase.credit_id,purchase.product_count from pcdata JOIN purchase ON pcdata.id = purchase.product_id where pcdata.id = ?",idList);
			user_productIdList.addAll(purchaseList);
		}

		List<PcDataDTO> purchasDataList = new ArrayList<>();
		for (Map<String, Object> map : user_productIdList) {

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
			pcdatadto.setCartId((int) map.get("cartId"));
			pcdatadto.setCreditId((int) map.get("credit_id"));
			pcdatadto.setProduct_count((int) map.get("product_count"));

			purchasDataList.add(pcdatadto);

		}

		return purchasDataList;

	}

	public Date selectPurchaseDate() {
		Date purchaseDate = jdbc.queryForObject("select purchase.purchase_date from purchase where purchase_date = (select max(purchase_date) from purchase);",Date.class);

		return purchaseDate;
	}


}
