package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.repository.CartDao;

@Service
public class CartService {

	@Autowired
	CartDao dao;

	public int insertOne(CartDTO cartdto, int product_id, int select_id) {
		int result = dao.insertOne(cartdto, product_id, select_id);

		return result;
	}

	public List<PcDataDTO> selectMany(String getName) {
		return dao.selectMany(getName);
	}

	public List<PcDataDTO> cartDataSelectMany() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String userId = auth.getName();

		return dao.cartDataSelectMany(userId);
	}

	public int selectOne(CartDTO cartdto, int product_id, int select_id) {
		return selectOne(cartdto, product_id, select_id);

	}

	public int deleteOne(int id, int getId) {

		return dao.deleteOne(id, getId);

	}

	public int updateOne(int productId, int newProductCount, int userId) {
		return dao.updateOne(productId, newProductCount, userId);

	}

	public boolean selectProductCount(int select_id) {
		// ログインユーザーの購入商品と購入数を取得
		List<CartDTO> checkProductCountList = dao.selectProductCount(select_id);

		for (int i = 0; i < checkProductCountList.size(); i++) {
			CartDTO cartdto = checkProductCountList.get(i);
			int productId = cartdto.getProduct_id();

			int productCount = cartdto.getProduct_count();

			int productStock = dao.productStockCheck(productId);
			int updateCheck = productStock - productCount;
			if (updateCheck < 0) {
				return false;
			}
		}

		List<CartDTO> productCountList = dao.selectProductCount(select_id);
		System.out.println("productCountList " + productCountList);
		for (int i = 0; i < productCountList.size(); i++) {
			CartDTO cartdto = productCountList.get(i);
			int productId = cartdto.getProduct_id();

			int productCount = cartdto.getProduct_count();

			// 各商品ごとに、在庫数から購入数を引く
			dao.productStockUpdate(productId, productCount);

		}

		return true;

	}

	public List<CartDTO> purchaseSelectMany(int select_id) {
		return dao.purchaseSelectMany(select_id);
	}

	public int idInsertOne(int id, int product_id, int select_id) {
		return dao.idInsertOne(id, product_id, select_id);
	}

	public int selectMaxId(int productId) {
		return dao.selectMaxId(productId);
	}

	public int updateCouponId(int cartId, int couponId) {

		return dao.updateCouponId(cartId, couponId);
	}

	public int couponCancelUpdate() {

		return dao.couponCancelUpdate();
	}

	public int updateMenberCouponId(int cartId, int couponId) {
		return dao.updateMenberCouponId(cartId, couponId);
	}

}
