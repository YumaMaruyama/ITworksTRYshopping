package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.repository.InquiryDao;

@Service
public class InquiryService {

	@Autowired
	InquiryDao dao;
	
	public int insertOne(InquiryDTO inquirydto,int select_id) {
		int result = dao.insertOne(inquirydto,select_id);
		if(result > 0) {
			System.out.println("insertOne(InquiryService)成功");
		}
		
		return result;
		
	}
}
