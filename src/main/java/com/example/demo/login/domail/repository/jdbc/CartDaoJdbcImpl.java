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
import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.repository.CartDao;

@Repository
public class CartDaoJdbcImpl implements CartDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(CartDTO cartdto, int product_id, int select_id) {

		//int getId = jdbc.queryForObject("select users.id from users where user_id = ?", Integer.class, select_id);

		int result = jdbc.update("insert into cart (id,"
				+ "user_id,"
				+ "product_id,"
				+ "product_count)"
				+ " value(?,?,?,?)", cartdto.getId(), select_id, product_id, 1);

		return result;
	}

	public List<PcDataDTO> selectMany(String getName) {
		//ログインユーザーのidを取得
		int getId = jdbc.queryForObject("select users.id from users where user_id = ?", Integer.class, getName);
		//ログインユーザーのidをもとに、カートに追加した商品をすべて取得
		List<Map<String, Object>> cartList = jdbc.queryForList("select cart.product_id from cart where user_id = ?",
				getId);

		//商品IDのカラム名と値の入ったMapからproduct_idListに値だけを入れる
		List<Integer> product_idList = new ArrayList<>();
		for (Map<String, Object> map : cartList) {
			int id = (int) map.get("product_id");
			product_idList.add(id);
		}
		int sumPrice = 0;
		//商品IDをもとに商品の情報を取り出す。
		List<Map<String, Object>> user_productList = new ArrayList<>();
		for (int i = 0; i < product_idList.size(); i++) {
			int pcdatadtoOne = product_idList.get(i);
			System.out.println("pcdatadtoOne" + pcdatadtoOne);
			List<Map<String, Object>> productList = jdbc.queryForList(
					"select pcdata.id,pcdata.company,pcdata.os,pcdata.pc_name,pcdata.pc_size,pcdata.price,pcdata.detail,pcdata.product_stock,pcdata.pcimg,pcdata.pcimg2,pcdata.pcimg3,cart.id AS cartId,cart.product_count,custom.id AS customId,custom.memory,custom.hard_disc,custom.cpu,custom.custom_price from pcdata JOIN cart ON pcdata.id = cart.product_id JOIN custom ON pcdata.id = custom.product_id where pcdata.id = ? and custom.user_id = ? and cart.user_id = ?",
					pcdatadtoOne,getId,getId);
			
			user_productList.addAll(productList);
			System.out.println("productList" + productList);

			//カート内剛健値段を出すためにユーザーのカート内のpriceを全て足していく

			List<Integer> priceList = new ArrayList<>();
			for (Map<String, Object> map : productList) {
				int getPrice = (int) map.get("price");
				sumPrice = sumPrice + getPrice;
				System.out.println("sumPrice" + sumPrice);
			}
			//			PcDataDTO pcdatadto = new PcDataDTO();
			//			pcdatadto.setTotalPrice(sumPrice);
			//			System.out.println("pcdatadto.totalPrice" + pcdatadto.getTotalPrice());

		}
		List<PcDataDTO> pcdataList = new ArrayList<>();
		for (Map<String, Object> map : user_productList) {

			PcDataDTO pcdatadto = new PcDataDTO();
			PcDetailDataDTO pcdetaildatadto = new PcDetailDataDTO();
			
			
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
			pcdatadto.setId((int)map.get("id"));
			pcdatadto.setMemory((String)map.get("memory"));
			pcdatadto.setHardDisc((String)map.get("hard_disc"));
			pcdatadto.setCpu((String)map.get("cpu"));
			pcdatadto.setCustomPrice((int)map.get("custom_price"));
			pcdatadto.setAfterCustomPrice(pcdatadto.getPrice() + pcdatadto.getCustomPrice());
			pcdatadto.setTotalPrice(sumPrice);
			
			
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
			System.out.println("id" + id);
		}
		System.out.println("idList" + idList);

		System.out.println("jcbcCartList " + cartList);
		List<Map<String, Object>> user_productList = new ArrayList<>();

		for (int i = 0; i < idList.size(); i++) {
			int pcdatadtoOne = idList.get(i);
			System.out.println("pcdatadtoOne" + pcdatadtoOne);
			List<Map<String, Object>> productList = jdbc.queryForList("select * from pcdata where id = ?",
					pcdatadtoOne);
			user_productList.addAll(productList);
			System.out.println("productList" + productList);

			int sum = 0;
			List<Integer> priceList = new ArrayList<>();
			for (Map<String, Object> map : productList) {
				//sum = sum + (int) map.get("price");
				int getPrice = (int) map.get("price");
				priceList.add(getPrice);
				System.out.println("priceList" + priceList);
			}
			PcDataDTO pcdatadto = new PcDataDTO();
			pcdatadto.setTotalPrice(sum);
			System.out.println("pcdatadto.totalPrice" + pcdatadto.getTotalPrice());

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
			int select_result = jdbc.queryForObject("select cart.product_id from cart where product_id = ? and user_id = ?",
					Integer.class, product_id, select_id);
			result = 1;
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteOne(int id,int getId) {
		System.out.println("deleteOne文到達");
		int result = jdbc.update("delete from cart where product_id = ? AND user_id = ?", id,getId);

		return result;
	}

	public int updateOne(int productId, int newProductCount,int userId) {
		System.out.println("daoUpdate到達");
		int result = jdbc.update("update cart set product_count = ? where product_id = ? AND user_id = ?", newProductCount, productId,userId);
		return result;
	}

	public List<CartDTO> selectProductCount(int select_id) {
		List<Map<String, Object>> map = jdbc.queryForList("select * from cart where user_id = ?",select_id);


		List<CartDTO> cartList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			CartDTO cartdto = new CartDTO();
		cartdto.setProduct_id((int)oneMap.get("product_id"));
		cartdto.setProduct_count((int)oneMap.get("product_count"));
		cartList.add(cartdto);
	}
		return cartList;
	}

	public int productStockUpdate(int productId,int productCount) {
		int result = jdbc.update("update pcdata set product_stock = product_stock - ? where id = ?",productCount,productId);
		return result;
	}

	public int productStockCheck(int productId) {
		int productStock = jdbc.queryForObject("select pcdata.product_stock from pcdata where id = ?",Integer.class,productId);

		return productStock;
	}

	public List<CartDTO> purchaseSelectMany(int select_id) {
		List<Map<String,Object>> map = jdbc.queryForList("select * from cart where user_id = ?",select_id);

		List<CartDTO> purchaseList = new ArrayList<>();
		for(Map<String,Object> oneMap : map) {
			CartDTO cartdto = new CartDTO();
			cartdto.setUser_id((int)oneMap.get("user_id"));
			cartdto.setProduct_id((int)oneMap.get("product_id"));
			cartdto.setProduct_count((int)oneMap.get("product_count"));
			purchaseList.add(cartdto);
		}
		return purchaseList;
	}
	
//	public int selectProductId(int cartId) {
//		int result = 
//	}
}
