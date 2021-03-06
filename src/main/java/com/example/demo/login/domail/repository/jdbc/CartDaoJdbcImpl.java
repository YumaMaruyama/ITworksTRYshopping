package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.repository.CartDao;

@Repository
public class CartDaoJdbcImpl implements CartDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(CartDTO cartdto, int product_id, int select_id) {

		int result = jdbc.update(
				"insert into cart (id," + "user_id," + "product_id," + "product_count)" + " value(?,?,?,?)",
				cartdto.getId(), select_id, product_id, 1);

		return result;
	}

	public List<PcDataDTO> selectMany(String getName) {
		// ログインユーザーのidを取得
		int getId = jdbc.queryForObject("select users.id from users where user_id = ?", Integer.class, getName);
		// ログインユーザーのidをもとに、カートに追加した商品をすべて取得
		List<Map<String, Object>> cartList = jdbc
				.queryForList("select cart.product_id from cart where user_id = ? and purchase_check is null", getId);

		// 商品IDのカラム名と値の入ったMapからproduct_idListに値だけを入れる
		List<Integer> product_idList = new ArrayList<>();
		for (Map<String, Object> map : cartList) {
			int id = (int) map.get("product_id");
			product_idList.add(id);
		}
		int sumPrice = 0;
		// 商品IDをもとに商品の情報を取り出す。
		List<Map<String, Object>> user_productList = new ArrayList<>();
		for (int i = 0; i < product_idList.size(); i++) {
			int pcdatadtoOne = product_idList.get(i);

			List<Map<String, Object>> productList = jdbc.queryForList(
					"select pcdata.id,pcdata.company,pcdata.os,pcdata.pc_name,pcdata.pc_size,pcdata.price,pcdata.detail,pcdata.product_stock,pcdata.pcimg,pcdata.pcimg2,pcdata.pcimg3,cart.id AS cartId,cart.product_count,cart.purchase_check,cart.coupon_id,cart.menber_coupon_check,custom.id AS customId,custom.memory,custom.hard_disc,custom.cpu,custom.custom_price from pcdata JOIN cart ON pcdata.id = cart.product_id JOIN custom ON pcdata.id = custom.product_id where pcdata.id = ? and custom.user_id = ? and cart.user_id = ? and cart.purchase_check is null and custom.purchase_check is null",
					pcdatadtoOne, getId, getId);

			user_productList.addAll(productList);

			// カート内剛健値段を出すためにユーザーのカート内のpriceを全て足していく
			List<Integer> priceList = new ArrayList<>();
			for (Map<String, Object> map : productList) {
				int getPrice = (int) map.get("price");
				sumPrice = sumPrice + getPrice;
			}

		}
		List<PcDataDTO> pcdataList = new ArrayList<>();
		for (Map<String, Object> map : user_productList) {

			PcDataDTO pcdatadto = new PcDataDTO();
			try {
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
				pcdatadto.setProduct_count((int) map.get("product_count"));
				pcdatadto.setCouponId((int) map.get("coupon_id"));
				pcdatadto.setMenberCouponCheck((String) map.get("menber_coupon_check"));
				pcdatadto.setId((int) map.get("id"));
				pcdatadto.setMemory((String) map.get("memory"));
				pcdatadto.setHardDisc((String) map.get("hard_disc"));
				pcdatadto.setCpu((String) map.get("cpu"));
				pcdatadto.setCustomPrice((int) map.get("custom_price"));
				pcdatadto.setAfterCustomPrice(pcdatadto.getPrice() + pcdatadto.getCustomPrice());
				pcdatadto.setTotalPrice(sumPrice);

			} catch (NullPointerException | EmptyResultDataAccessException e) {
				e.printStackTrace();
				int couponId = 0;

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
				pcdatadto.setProduct_count((int) map.get("product_count"));
				pcdatadto.setCouponId(couponId);
				pcdatadto.setMenberCouponCheck("クーポン不使用");
				pcdatadto.setId((int) map.get("id"));
				pcdatadto.setMemory((String) map.get("memory"));
				pcdatadto.setHardDisc((String) map.get("hard_disc"));
				pcdatadto.setCpu((String) map.get("cpu"));
				pcdatadto.setCustomPrice((int) map.get("custom_price"));
				pcdatadto.setAfterCustomPrice(pcdatadto.getPrice() + pcdatadto.getCustomPrice());
				pcdatadto.setTotalPrice(sumPrice);

			}

			pcdataList.add(pcdatadto);

		}

		return pcdataList;
	}

	public List<PcDataDTO> cartDataSelectMany(String user_id) {
		int getId = jdbc.queryForObject("select users.id from users where user_id = ?", Integer.class, user_id);
		List<Map<String, Object>> cartList = jdbc.queryForList("select cart.product_id from cart where user_id = ?",
				getId);

		List<Integer> idList = new ArrayList<>();
		for (Map<String, Object> map : cartList) {

			int id = (int) map.get("product_id");
			idList.add(id);

		}

		List<Map<String, Object>> user_productList = new ArrayList<>();

		for (int i = 0; i < idList.size(); i++) {
			int pcdatadtoOne = idList.get(i);

			List<Map<String, Object>> productList = jdbc.queryForList("select * from pcdata where id = ?",
					pcdatadtoOne);
			user_productList.addAll(productList);

			int sum = 0;
			List<Integer> priceList = new ArrayList<>();
			for (Map<String, Object> map : productList) {

				int getPrice = (int) map.get("price");
				priceList.add(getPrice);

			}
			PcDataDTO pcdatadto = new PcDataDTO();
			pcdatadto.setTotalPrice(sum);

		}
		List<PcDataDTO> pcdataList = new ArrayList<>();
		for (Map<String, Object> map : user_productList) {

			PcDataDTO pcdatadto = new PcDataDTO();

			pcdatadto.setId((int) map.get("id"));
			pcdatadto.setPrice((int) map.get("price"));
			pcdatadto.setProduct_stock((int) map.get("product_stock"));
			pcdataList.add(pcdatadto);

		}
		return pcdataList;
	}

	public int selectOne(CartDTO cartdto, int product_id, int select_id) throws EmptyResultDataAccessException {

		int result = 0;

		try {
			int select_result = jdbc.queryForObject(
					"select cart.product_id from cart where product_id = ? and user_id = ? and purchase_check is null",
					Integer.class, product_id, select_id);
			result = 1;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteOne(int id, int getId) {

		int result = jdbc.update("delete from cart where product_id = ? AND user_id = ? and purchase_check is null", id,
				getId);

		return result;
	}

	public int updateOne(int productId, int newProductCount, int userId) {

		int result = jdbc.update(
				"update cart set product_count = ? where product_id = ? AND user_id = ? and purchase_check is null",
				newProductCount, productId, userId);
		return result;
	}

	public List<CartDTO> selectProductCount(int select_id) {
		List<Map<String, Object>> map = jdbc
				.queryForList("select * from cart where user_id = ? and purchase_check is null", select_id);

		List<CartDTO> cartList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			CartDTO cartdto = new CartDTO();
			cartdto.setProduct_id((int) oneMap.get("product_id"));
			cartdto.setProduct_count((int) oneMap.get("product_count"));
			cartList.add(cartdto);
		}
		return cartList;
	}

	public int productStockUpdate(int productId, int productCount) {
		int result = jdbc.update("update pcdata set product_stock = product_stock - ? where id = ?", productCount,
				productId);
		return result;
	}

	public int productStockCheck(int productId) {
		int productStock = jdbc.queryForObject("select pcdata.product_stock from pcdata where id = ?", Integer.class,
				productId);

		return productStock;
	}

	public List<CartDTO> purchaseSelectMany(int select_id) {
		List<Map<String, Object>> map = jdbc
				.queryForList("select * from cart where user_id = ? and purchase_check is null", select_id);

		List<CartDTO> purchaseList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			CartDTO cartdto = new CartDTO();
			cartdto.setId((int) oneMap.get("id"));
			cartdto.setUser_id((int) oneMap.get("user_id"));
			cartdto.setProduct_id((int) oneMap.get("product_id"));
			cartdto.setProduct_count((int) oneMap.get("product_count"));
			try {
				cartdto.setMenberCouponCheck((String) oneMap.get("menber_coupon_check"));
				cartdto.setCouponId((int) oneMap.get("coupon_id"));
			} catch (NullPointerException e) {
				
				e.printStackTrace();
				cartdto.setCouponId(-1);
				cartdto.setMenberCouponCheck("no");
			}

			purchaseList.add(cartdto);
		}
		return purchaseList;
	}

	public int idInsertOne(int id, int product_id, int select_id) {
		int result = jdbc.update(
				"update cart set purchase_check = ? where product_id = ? and user_id = ? and purchase_check is null",
				id, product_id, select_id);

		return result;
	}

	public int selectMaxId(int productId) {
		int result = jdbc.queryForObject("select max(id) from cart where product_id = ?", Integer.class, productId);
		return result;
	}

	public int updateCouponId(int cartId, int couponId) {
		int result = jdbc.update("update cart set coupon_id = ? where id = ?", couponId, cartId);
	
		return result;
	}

	public int couponCancelUpdate() {
		int result = jdbc.update("update cart set coupon_id = null and purchase_check is null");
		
		return result;
	}

	public int updateMenberCouponId(int cartId, int couponId) {
		
		int result = jdbc.update("update cart set coupon_id = ?,menber_coupon_check = '会員ランク特典使用' where id = ?",
				couponId, cartId);
		return result;
	}

}
