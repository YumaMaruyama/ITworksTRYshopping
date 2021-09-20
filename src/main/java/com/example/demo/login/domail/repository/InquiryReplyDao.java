package com.example.demo.login.domail.repository;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.InquiryReplyDTO;

public interface InquiryReplyDao {

	public int insertOne(InquiryReplyDTO inquiryreplydto,int maxId);
	
	public int replyUpdateOne(HttpSession session,InquiryReplyDTO inquiryreplydto);

	public InquiryReplyDTO selectOne(int inquiryId);
	
	public int deleteOne(int inquiryId);
}
