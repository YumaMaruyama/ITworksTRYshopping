package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CouponDTO;
import com.example.demo.login.domail.repository.CouponDao;

@Repository
public class CouponDaoJdbcImpl implements CouponDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int couponInsert(CouponDTO coupondto,HttpSession session) {
		int result = jdbc.update("insert into coupon (id,"
				+ " discount,"
				+ " purchase_count_target,"
				+ " purchase_total_price_target,"
				+ " title)"
				+ " value(?,?,?,?,?)",coupondto.getId(),session.getAttribute("discount"),session.getAttribute("purchaseCountTarget"),session.getAttribute("purchaseTotalPriceTarget"),session.getAttribute("title"));
		
		return result;
	}
	
	public List<CouponDTO> selectMany() {
		
		List<Map<String,Object>> map = jdbc.queryForList("select * from coupon");
		
		List<CouponDTO> coupondtoList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			CouponDTO coupondto = new CouponDTO();
			coupondto.setId((int)oneMap.get("id"));
			coupondto.setDiscount((int)oneMap.get("discount"));
			coupondto.setPurchaseCountTarget((int)oneMap.get("purchase_count_target"));
			coupondto.setPurchaseTotalPriceTarget((int)oneMap.get("purchase_total_price_target"));
			coupondto.setTitle((String)oneMap.get("title"));
			coupondto.setRegistratonDate((Date)oneMap.get("registration_date"));
			
			coupondtoList.add(coupondto);
		}
		
		return coupondtoList;
				
	}
	
	public CouponDTO selectOne(int couponId) {
		Map<String,Object> map = jdbc.queryForMap("select * from coupon where id = ?",couponId);
		CouponDTO coupondto = new CouponDTO();
		
		coupondto.setDiscount((int)map.get("discount"));
		
		return coupondto;
	}
	
	public List<Integer> selectIdMany() {
		List<Integer> couponId = jdbc.queryForList("select coupon.id from coupon",Integer.class);
		return couponId;
		
	}
}
