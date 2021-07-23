package com.example.demo.login.domail.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.PcDataDTO;

public interface CartDao {

	public int insertOne(CartDTO cartdto,int product_id,int select_id);

	public List<PcDataDTO> selectMany(String getName);

	public List<PcDataDTO> cartDataSelectMany(String user_id);

	public int deleteOne(int id,int getId);

	public int updateOne(int productId,int newProductCount,int userId);

	public int selectOne(CartDTO cartdto,int product_id,int select_id) throws EmptyResultDataAccessException;

	public List<CartDTO> selectProductCount(int select_id);

	public int productStockUpdate(int productId,int productCount);

	public int productStockCheck(int productId);

	public List<CartDTO> purchaseSelectMany(int select_id);
	
	public int idInsertOne(int id,int product_id ,int select_id);
	
	

	//public int selectProductId(int cartId);

}
