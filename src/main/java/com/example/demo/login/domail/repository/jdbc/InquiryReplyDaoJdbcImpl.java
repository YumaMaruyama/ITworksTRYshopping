package com.example.demo.login.domail.repository.jdbc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.InquiryReplyDTO;
import com.example.demo.login.domail.repository.InquiryReplyDao;

@Repository
public class InquiryReplyDaoJdbcImpl implements InquiryReplyDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public int insertOne(InquiryReplyDTO inquiryreplydto,int maxId) {
		int result = jdbc.update("insert into inquiry_reply (id,"
				+ " inquiry_id,"
				+ " title,"
				+ " content)"
				+ " value(?,?,?,?)",inquiryreplydto.getId(),maxId,"返信なし","返信なし");
		
		return result;
	}
	
	public int replyUpdateOne(HttpSession session,InquiryReplyDTO inquiryreplydto) {
		int result = jdbc.update("update inquiry_reply set title = ?,content = ? where inquiry_id = ?",session.getAttribute("title"),session.getAttribute("content"),session.getAttribute("id"));
		return result;
	}
}
