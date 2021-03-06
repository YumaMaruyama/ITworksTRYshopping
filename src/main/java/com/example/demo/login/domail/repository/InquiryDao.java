package com.example.demo.login.domail.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.InquiryAllDTO;
import com.example.demo.login.domail.model.InquiryDTO;

public interface InquiryDao {

	public int insertOne(InquiryDTO inquirydto, int select_id);

	public int beforLoginInquiryInsertOne(InquiryDTO inquirydto, HttpSession session);

	public List<InquiryDTO> selectMany();

	public InquiryDTO selectOne(int id);

	public List<InquiryAllDTO> everyUserSelectMany(int select_id);

	public int userDeletionOne(int inquiryId);

	public int deleteOne(int id);

	public int selectMaxId();
	
	public List<InquiryDTO> beforeLoginSelectMany(String userIdCheck);
	
	public InquiryDTO beforeLoginSelectOne(int inquiryId);
}
