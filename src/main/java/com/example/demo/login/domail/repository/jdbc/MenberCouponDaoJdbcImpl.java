package com.example.demo.login.domail.repository.jdbc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.MenberCouponDTO;
import com.example.demo.login.domail.repository.MenberCouponDao;
@Repository
public class MenberCouponDaoJdbcImpl implements MenberCouponDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int menberCouponInsert(MenberCouponDTO menbercoupondto,HttpSession session) {
		
		int result = jdbc.update("insert into menber_coupon (id,"
				+ " discount,"
				+ " purchase_count_target,"
				+ " purchase_total_price_target,"
				+ " title,"
				+ " coupon_rank)"
				+ " value(?,?,?,?,?,?)",menbercoupondto.getId(),session.getAttribute("discount"),session.getAttribute("purchaseCountTarget"),session.getAttribute("purchaseTotalPriceTarget"),session.getAttribute("title"),session.getAttribute("couponRank"));
		
		return result;
	}
	
	public int selectMany(int rankNumber) {
		
	}
}
