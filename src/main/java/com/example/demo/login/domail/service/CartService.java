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

		if (result > 0) {
			System.out.println("insert成功");
		}

		return result;
	}

	public List<PcDataDTO> selectMany(String getName) {
		return dao.selectMany(getName);
	}

	public List<PcDataDTO> cartDataSelectMany() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth" + auth.getName());
		String getName = auth.getName();

		return dao.cartDataSelectMany(getName);
	}

	public int selectOne(CartDTO cartdto, int product_id, int select_id) {
		int result = dao.selectOne(cartdto, product_id, select_id);

		if (result > 0) {
			System.out.println("select成功");
		}
		return result;
	}

	public int deleteOne(int id, int getId) {

		int result = dao.deleteOne(id, getId);

		if (result > 0) {
			System.out.println("delete成功");
		}
		return result;
	}

	public int updateOne(int productId, int newProductCount, int userId) {
		int result = dao.updateOne(productId, newProductCount, userId);

		if (result > 0) {
			System.out.println("update成功");
		} else {
			System.out.println("update失敗");
		}
		return result;
	}

	public boolean selectProductCount(int select_id) {
		//ログインユーザーの購入商品と購入数を取得
		List<CartDTO> checkProductCountList = dao.selectProductCount(select_id);
		System.out.println("productCountList " + checkProductCountList);
		for (int i = 0; i < checkProductCountList.size(); i++) {
			CartDTO cartdto = checkProductCountList.get(i);
			int productId = cartdto.getProduct_id();
			System.out.println("productId " + productId);
			int productCount = cartdto.getProduct_count();
			System.out.println("productCount " + productCount);

			int productStock = dao.productStockCheck(productId);
			int updateCheck = productStock - productCount;
			System.out.println("test" + updateCheck +" " + productStock + " " +  productCount);
			if(updateCheck < 0) {
				return false;
			}
		}

		List<CartDTO> productCountList = dao.selectProductCount(select_id);
		System.out.println("productCountList " + productCountList);
		for (int i = 0; i < productCountList.size(); i++) {
			CartDTO cartdto = productCountList.get(i);
			int productId = cartdto.getProduct_id();
			System.out.println("productId " + productId);
			int productCount = cartdto.getProduct_count();
			System.out.println("productCount " + productCount);
			//各商品ごとに、在庫数から購入数を引く
			int result = dao.productStockUpdate(productId, productCount);
			if (result > 0) {
				System.out.println("update成功");
			} else {
				System.out.println("update失敗");
			}
		}

		return true;

	}

	public List<CartDTO> purchaseSelectMany(int select_id) {
		return dao.purchaseSelectMany(select_id);
	}
}
