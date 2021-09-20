package com.example.demo.login.domail.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.InquiryAllDTO;
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
	
	public List<InquiryDTO> selectMany() {
		return dao.selectMany();
	}

	public InquiryDTO selectOne(int id) {
		return dao.selectOne(id);
	}
	
	public int userDeletionOne(int inquiryId) {
		return dao.userDeletionOne(inquiryId);
	}
	
	public List<InquiryAllDTO> everyUserSelectMany(int select_id) {
		return dao.everyUserSelectMany(select_id);
	}
	
	public int deleteOne(int id) {
		return dao.deleteOne(id);
	}
	
	public int selectMaxId() {
		return dao.selectMaxId();
	}
	
	public int beforLoginInquiryInsertOne(InquiryDTO inquirydto,HttpSession session) {
		return dao.beforLoginInquiryInsertOne(inquirydto,session);
	}
}
