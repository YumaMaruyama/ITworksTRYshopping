package com.example.demo.login.domail.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.repository.InquiryDao;

@Repository
public class InquiryJdbcDaoImpl implements InquiryDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(InquiryDTO inquirydto,int select_id) {
		int result = jdbc.update("insert into inquiry (id,"
				+ " user_id,"
				+ " title,"
				+ " content)"
				+ " value(?,?,?,?)",inquirydto.getId(),select_id,inquirydto.getTitle(),inquirydto.getContent());
				
		return result;
	}
}
