package com.example.demo.login.domail.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domail.model.InquiryAllDTO;
import com.example.demo.login.domail.model.InquiryDTO;
import com.example.demo.login.domail.repository.InquiryDao;

@Repository
public class InquiryJdbcDaoImpl implements InquiryDao {

	@Autowired
	JdbcTemplate jdbc;

	public int insertOne(InquiryDTO inquirydto, int select_id) {
		int result = jdbc.update("insert into inquiry (id," + " user_id," + " title," + " content)" + " value(?,?,?,?)",
				inquirydto.getId(), select_id, inquirydto.getTitle(), inquirydto.getContent());

		return result;
	}

	public int beforLoginInquiryInsertOne(InquiryDTO inquirydto, HttpSession session) {
		int result = jdbc.update(
				"insert into inquiry (id," + " mail_address," + " user_id," + " title," + " content)"
						+ " value(?,?,?,?,?)",
				inquirydto.getId(), session.getAttribute("mailAddress"), -1, session.getAttribute("title"),
				session.getAttribute("content"));
		return result;
	}

	public List<InquiryDTO> selectMany() {
		List<Map<String, Object>> map = jdbc.queryForList(
				"select inquiry.id,inquiry.user_id,inquiry.title,inquiry.content,inquiry.registration_date,inquiry.mail_address,users.user_name  from inquiry join users on inquiry.user_id = users.id");

		List<InquiryDTO> inquiryList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			InquiryDTO inquirydto = new InquiryDTO();
			inquirydto.setId((int) oneMap.get("id"));
			inquirydto.setUser_id((int) oneMap.get("user_id"));
			inquirydto.setTitle((String) oneMap.get("title"));
			inquirydto.setContent((String) oneMap.get("content"));
			inquirydto.setRegistrationDate((Date) oneMap.get("registration_date"));
			inquirydto.setMailAddress((String)oneMap.get("mail_address"));
			inquirydto.setUserName((String) oneMap.get("user_name"));

			inquiryList.add(inquirydto);
		}

		return inquiryList;
	}

	public InquiryDTO selectOne(int id) {
		Map<String, Object> map = jdbc.queryForMap(
				"select inquiry.id,inquiry.user_id,inquiry.title,inquiry.content,inquiry.registration_date,users.user_name  from inquiry join users on inquiry.user_id = users.id where inquiry.id = ?",
				id);

		InquiryDTO inquirydto = new InquiryDTO();
		inquirydto.setId((int) map.get("id"));
		inquirydto.setUserName((String) map.get("user_name"));
		inquirydto.setTitle((String) map.get("title"));
		inquirydto.setContent((String) map.get("content"));
		inquirydto.setRegistrationDate((Date) map.get("registration_date"));

		return inquirydto;
	}

	public List<InquiryAllDTO> everyUserSelectMany(int select_id) {
		List<Map<String, Object>> map = jdbc.queryForList(
				"select inquiry.id,inquiry.title,inquiry.content,inquiry.registration_date,inquiry_reply.title as adminTitle,inquiry_reply.content as adminContent,inquiry_reply.registration_date as adminRegistration_date from inquiry join inquiry_reply on inquiry.id = inquiry_reply.inquiry_id");

		List<InquiryAllDTO> inquiryList = new ArrayList<>();
		for (Map<String, Object> oneMap : map) {
			InquiryAllDTO inquiryalldto = new InquiryAllDTO();
			inquiryalldto.setId((int) oneMap.get("id"));
			inquiryalldto.setTitle((String) oneMap.get("title"));
			inquiryalldto.setContent((String) oneMap.get("content"));
			inquiryalldto.setRegistrationDate((Date) oneMap.get("registration_date"));
			inquiryalldto.setAdminTitle((String) oneMap.get("adminTitle"));
			inquiryalldto.setAdminContent((String) oneMap.get("adminContent"));
			inquiryalldto.setAdminRegistrationDate((Date) oneMap.get("adminRegistration_date"));

			inquiryList.add(inquiryalldto);
		}
		return inquiryList;

	}

	public int userDeletionOne(int inquiryId) {
		int result = jdbc.update("delete from inquiry where id = ?", inquiryId);

		return result;
	}

	public int deleteOne(int id) {
		int result = jdbc.update("delete from inquiry where id = ?", id);

		return result;
	}

	public int selectMaxId() {

		int maxId = jdbc.queryForObject("select max(id) from inquiry", Integer.class);

		return maxId;
	}
	
	public List<InquiryDTO> beforeLoginSelectMany(String userIdCheck) {
		List<Map<String,Object>> map = jdbc.queryForList("select * from inquiry where user_id = ?",userIdCheck);
		
		List<InquiryDTO> inquiryBeforeLoginList = new ArrayList<>();
		
		for(Map<String,Object> oneMap : map) {
			InquiryDTO inquirydto = new InquiryDTO();
			
			inquirydto.setId((int) oneMap.get("id"));
			inquirydto.setUser_id((int) oneMap.get("user_id"));
			inquirydto.setTitle((String) oneMap.get("title"));
			inquirydto.setContent((String) oneMap.get("content"));
			inquirydto.setRegistrationDate((Date) oneMap.get("registration_date"));
			inquirydto.setMailAddress((String)oneMap.get("mail_address"));
			
			inquiryBeforeLoginList.add(inquirydto);
		}
		
		return inquiryBeforeLoginList;
	}
	
	public InquiryDTO beforeLoginSelectOne(int inquiryId) {
		Map<String,Object> map = jdbc.queryForMap("select * from inquiry where id = ?",inquiryId);
		
		InquiryDTO inquirydto = new InquiryDTO();
		inquirydto.setId((int) map.get("id"));
		inquirydto.setUser_id((int) map.get("user_id"));
		inquirydto.setTitle((String) map.get("title"));
		inquirydto.setContent((String) map.get("content"));
		inquirydto.setRegistrationDate((Date) map.get("registration_date"));
		inquirydto.setMailAddress((String)map.get("mail_address"));
		
		return inquirydto;
	}

}
