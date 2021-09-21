package com.example.demo.login.domail.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.InquiryReplyDTO;
import com.example.demo.login.domail.repository.InquiryReplyDao;

@Service
public class InquiryReplyService {

	@Autowired
	InquiryReplyDao dao;

	public int insertOne(InquiryReplyDTO inquiryreplydto, int maxId) {

		return dao.insertOne(inquiryreplydto, maxId);
	}

	public int replyUpdateOne(HttpSession session, InquiryReplyDTO inquiryreplydto) {
		return dao.replyUpdateOne(session, inquiryreplydto);
	}

	public InquiryReplyDTO selectOne(int inquiryId) {
		return dao.selectOne(inquiryId);
	}

	public int deleteOne(int inquiryId) {
		return dao.deleteOne(inquiryId);
	}

}
