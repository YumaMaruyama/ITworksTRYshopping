package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.NewsDTO;

public interface NewsDao {

	public int insertOne(NewsDTO newsdto);
	
	public List<NewsDTO> selectMany();
	
	public NewsDTO selectOne(int newsId);
	
	public int deleteOne(int newsId);
}
