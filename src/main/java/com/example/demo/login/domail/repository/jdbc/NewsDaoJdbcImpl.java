package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.NewsDTO;
import com.example.demo.login.domail.repository.NewsDao;

@Repository
public class NewsDaoJdbcImpl implements NewsDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(NewsDTO newsdto) {
		 int result= jdbc.update("insert into news (id,"
				+ " title,"
				+ " content)"
				+ " value(?,?,?)",newsdto.getId(),newsdto.getTitle(),newsdto.getContent());
	return result;
	}
	
	public List<NewsDTO> selectMany() {
		List<Map<String,Object>> map = jdbc.queryForList("select * from news");
		
		List<NewsDTO> newsdtoList = new ArrayList<>();
		for(Map<String,Object> oneMap:map) {
			NewsDTO newsdto = new NewsDTO();
			newsdto.setId((int)oneMap.get("id"));
			newsdto.setTitle((String)oneMap.get("title"));
			newsdto.setContent((String)oneMap.get("content"));
			newsdto.setRegistrationDate((Date)oneMap.get("registration_date"));
			
			newsdtoList.add(newsdto);
	}
		return newsdtoList;
}
}
