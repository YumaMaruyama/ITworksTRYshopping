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

	public int insertOne(CartDTO cartdto,int product_id,String user_id ) {
		int result = dao.insertOne(cartdto,product_id,user_id);

		if(result > 0) {
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


	public int deleteOne(int id) {

		int result = dao.deleteOne(id);

		if(result > 0) {
			System.out.println("delete成功");
		}
		return result;
	}

	public int updateOne(int id,int newProductCount) {
		int result = dao.updateOne(id,newProductCount);

		if(result > 0) {
			System.out.println("update成功");
		}
		return result;
	}
}
