package com.example.demo.login.domail.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.MenberCouponDTO;
import com.example.demo.login.domail.repository.MenberCouponDao;

@Service
public class MenberCouponService {

	@Autowired
	MenberCouponDao dao;

	public int menberCouponInsert(MenberCouponDTO menbercoupondto, HttpSession session) {
		return dao.menberCouponInsert(menbercoupondto, session);
	}
	
	public List<MenberCouponDTO> selectMany(int rankNumber) {
		return dao.selectMany(rankNumber);
	}
	
	public List<MenberCouponDTO> selectMany() {
		return dao.selectMany();
	}
	
	public List<MenberCouponDTO> selectRankNumberCheckMany(int rankNumber) {
		return dao.selectRankNumberCheckMany(rankNumber);
	}
	
	public MenberCouponDTO selectOne(int couponId) {
		return dao.selectOne(couponId);
	}
	
	public List<Integer> selectMenberCouponId(int rankNumber) {
		return dao.selectMenberCouponId(rankNumber);
		
	}
	
	public List<Integer> selectMenberCouponId() {
		return dao.selectMenberCouponId();
		
	}
	
	public List<MenberCouponDTO> selectManyBeforeCoupon(int menberCouponId) {
		return dao.selectManyBeforeCoupon(menberCouponId);
	}
	
	public List<MenberCouponDTO> selectManyBeforeMenberCoupon(int menberCouponId,int rankNumber) {
		return dao.selectManyBeforeMenberCoupon(menberCouponId,rankNumber);
	}
}
