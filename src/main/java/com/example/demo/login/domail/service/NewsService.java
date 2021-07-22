package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.NewsDTO;
import com.example.demo.login.domail.repository.NewsDao;

@Service
public class NewsService {

	@Autowired
	NewsDao dao;
	
	public int insertOne(NewsDTO newsdto) {
		return dao.insertOne(newsdto);
	}
	
	public List<NewsDTO> selectMany() {
		return dao.selectMany();
	}
}
