package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.CartDTO;
import com.example.demo.login.domail.model.PcDataDTO;

public interface CartDao {

	public int insertOne(CartDTO cartdto,int product_id,String user_id);

	public List<PcDataDTO> selectMany(String getName);

	public List<PcDataDTO> cartDataSelectMany(String user_id);

	public int deleteOne(int product_id,int getId);
}
