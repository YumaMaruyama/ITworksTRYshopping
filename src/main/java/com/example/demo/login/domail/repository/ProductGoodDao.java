package com.example.demo.login.domail.repository;

import java.util.List;

public interface ProductGoodDao {

	
	public int insertOne(int userId,int productId);
	
	public List<Integer> selectMany(int userId);
	
	public int goodCheck(int userId,int productId);
	
	public int deleteOne(int userId,int productId);
}
