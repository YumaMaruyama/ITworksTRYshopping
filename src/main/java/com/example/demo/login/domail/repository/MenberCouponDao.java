package com.example.demo.login.domail.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.login.domail.model.MenberCouponDTO;

public interface MenberCouponDao {

	public int menberCouponInsert(MenberCouponDTO menbercoupondto, HttpSession session);

	public List<MenberCouponDTO> selectMany(int rankNumber);

	public MenberCouponDTO selectOne(int couponId);

	public List<Integer> selectMenberCouponId(int rankNumber);

	public List<MenberCouponDTO> selectManyBeforeCoupon(int menberCouponId);

	public MenberCouponDTO selectManyBeforeMenberCoupon(int menberCouponId);

	public List<MenberCouponDTO> selectMany();

	public List<MenberCouponDTO> selectRankNumberCheckMany(int rankNumber);

	public List<Integer> selectMenberCouponId();
}
