package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	public int menberCouponInsert(MenberCouponDTO menbercoupondto, HttpSession session) {

		int result = jdbc.update(
				"insert into menber_coupon (id," + " discount," + " purchase_count_target,"
						+ " purchase_total_price_target," + " title," + " coupon_rank)" + " value(?,?,?,?,?,?)",
				menbercoupondto.getId(), session.getAttribute("discount"), session.getAttribute("purchaseCountTarget"),
				session.getAttribute("purchaseTotalPriceTarget"), session.getAttribute("title"),
				session.getAttribute("couponRank"));

		return result;
	}

	public List<MenberCouponDTO> selectMany(int rankNumber) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from menber_coupon where coupon_rank <= ?",
				rankNumber);

		List<MenberCouponDTO> menbercoupondtoList = new ArrayList<>();

		for (Map<String, Object> oneMap : map) {
			MenberCouponDTO menbercoupondto = new MenberCouponDTO();
			menbercoupondto.setId((int) oneMap.get("id"));
			menbercoupondto.setDiscount((int) oneMap.get("discount"));
			menbercoupondto.setPurchaseCountTarget((int) oneMap.get("purchase_count_target"));
			menbercoupondto.setPurchaseTotalPriceTarget((int) oneMap.get("purchase_total_price_target"));
			menbercoupondto.setTitle((String) oneMap.get("title"));
			menbercoupondto.setCouponRank((int) oneMap.get("coupon_rank"));

			menbercoupondtoList.add(menbercoupondto);
		}

		return menbercoupondtoList;
	}

	public List<MenberCouponDTO> selectMany() {
		List<Map<String, Object>> map = jdbc.queryForList("select * from menber_coupon");

		List<MenberCouponDTO> coupondtoList = new ArrayList<>();

		for (Map<String, Object> oneMap : map) {
			MenberCouponDTO coupondto = new MenberCouponDTO();
			coupondto.setId((int) oneMap.get("id"));
			coupondto.setDiscount((int) oneMap.get("discount"));
			coupondto.setPurchaseCountTarget((int) oneMap.get("purchase_count_target"));
			coupondto.setPurchaseTotalPriceTarget((int) oneMap.get("purchase_total_price_target"));
			coupondto.setTitle((String) oneMap.get("title"));
			coupondto.setCouponRank((int) oneMap.get("coupon_rank"));
			coupondtoList.add(coupondto);
		}

		return coupondtoList;

	}

	public List<MenberCouponDTO> selectRankNumberCheckMany(int rankNumber) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from menber_coupon where coupon_rank <= ?",
				rankNumber);

		List<MenberCouponDTO> coupondtoList = new ArrayList<>();

		for (Map<String, Object> oneMap : map) {
			MenberCouponDTO coupondto = new MenberCouponDTO();
			coupondto.setId((int) oneMap.get("id"));
			coupondto.setDiscount((int) oneMap.get("discount"));
			coupondto.setPurchaseCountTarget((int) oneMap.get("purchase_count_target"));
			coupondto.setPurchaseTotalPriceTarget((int) oneMap.get("purchase_total_price_target"));
			coupondto.setTitle((String) oneMap.get("title"));
			coupondto.setCouponRank((int) oneMap.get("coupon_rank"));
			coupondtoList.add(coupondto);
		}

		return coupondtoList;

	}

	public MenberCouponDTO selectManyBeforeMenberCoupon(int menberCouponId) {
		Map<String, Object> map = jdbc.queryForMap("select * from menber_coupon where id = ?", menberCouponId);

		MenberCouponDTO coupondto = new MenberCouponDTO();
		coupondto.setId((int) map.get("id"));
		coupondto.setDiscount((int) map.get("discount"));
		coupondto.setPurchaseCountTarget((int) map.get("purchase_count_target"));
		coupondto.setPurchaseTotalPriceTarget((int) map.get("purchase_total_price_target"));
		coupondto.setTitle((String) map.get("title"));
		coupondto.setCouponRank((int) map.get("coupon_rank"));
		return coupondto;

	}

	public List<MenberCouponDTO> selectManyBeforeCoupon(int menberCouponId) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from menber_coupon where id = ?", menberCouponId);

		List<MenberCouponDTO> menbercoupondtoList = new ArrayList<>();

		for (Map<String, Object> oneMap : map) {
			MenberCouponDTO menbercoupondto = new MenberCouponDTO();
			menbercoupondto.setId((int) oneMap.get("id"));
			menbercoupondto.setDiscount((int) oneMap.get("discount"));
			menbercoupondto.setPurchaseCountTarget((int) oneMap.get("purchase_count_target"));
			menbercoupondto.setPurchaseTotalPriceTarget((int) oneMap.get("purchase_total_price_target"));
			menbercoupondto.setTitle((String) oneMap.get("title"));
			menbercoupondto.setCouponRank((int) oneMap.get("coupon_rank"));

			menbercoupondtoList.add(menbercoupondto);
		}

		return menbercoupondtoList;
	}

	public MenberCouponDTO selectOne(int couponId) {
		Map<String, Object> map = jdbc.queryForMap("select * from menber_coupon where id = ?", couponId);

		MenberCouponDTO menbercoupondto = new MenberCouponDTO();
		menbercoupondto.setDiscount((int) map.get("discount"));

		return menbercoupondto;
	}

	public List<Integer> selectMenberCouponId(int rankNumber) {
		List<Integer> menberCouponList = jdbc.queryForList(
				"select menber_coupon.id from menber_coupon where coupon_rank <= ?", Integer.class, rankNumber);
		return menberCouponList;

	}

	public List<Integer> selectMenberCouponId() {
		List<Integer> menberCouponList = jdbc.queryForList("select menber_coupon.id from menber_coupon", Integer.class);
		return menberCouponList;
	}

}
