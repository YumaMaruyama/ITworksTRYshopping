package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.InquiryAllDTO;
import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.model.InquiryReplyDTO;

public interface InquiryDao {

	public int insertOne(InquiryDTO inquirydto,int select_id);
	
	public List<InquiryDTO> selectMany();
	
	public InquiryDTO selectOne(int id);
	
	public int replyInsertOne(InquiryReplyDTO inquiryreplydto);
	
	public List<InquiryAllDTO> everyUserSelectMany(int select_id);
}
