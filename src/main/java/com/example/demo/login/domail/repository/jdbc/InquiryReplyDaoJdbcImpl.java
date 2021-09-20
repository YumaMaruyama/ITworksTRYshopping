package com.example.demo.login.domail.repository.jdbc;



import java.util.Date;
import java.util.Map;

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
	
	public InquiryReplyDTO selectOne(int inquiryId) {
		Map<String,Object> map = jdbc.queryForMap("select * from inquiry_reply where inquiry_id = ?",inquiryId);
		
		InquiryReplyDTO inquiryreplydto = new InquiryReplyDTO();
		inquiryreplydto.setId((int)map.get("id"));
		inquiryreplydto.setTitle((String)map.get("title"));
		inquiryreplydto.setContent((String)map.get("content"));
		inquiryreplydto.setRegistrationDate((Date)map.get("registration_date"));
		
		return inquiryreplydto;	
	}
	
	public int deleteOne(int inquiryId) {
		int result = jdbc.update("delete from inquiry_reply where inquiry_id = ?",inquiryId);
		
		return result;
	}
}
