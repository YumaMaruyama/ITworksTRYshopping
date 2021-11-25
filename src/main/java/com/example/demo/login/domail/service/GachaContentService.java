package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.GachaContentDTO;
import com.example.demo.login.domail.repository.GachaContentDao;

@Service
public class GachaContentService {

	@Autowired
	GachaContentDao dao;
	
	public GachaContentDTO selectFiveSS() {
		return dao.selectFiveSS();
	}
	
	public GachaContentDTO selectFourSS() {
		return dao.selectFourSS();
	}
	
	public GachaContentDTO selectFourS() {
		return dao.selectFourS();
	}
	
	public GachaContentDTO selectFourA() {
		return dao.selectFourA();
	}
	
	public GachaContentDTO selectFourB() {
		return dao.selectFourB();
	}
	
	public GachaContentDTO selectFourC() {
		return dao.selectFourC();
	}
	
	public GachaContentDTO selectThreeSS() {
		return dao.selectThreeSS();
	}
	
	public GachaContentDTO selectThreeS() {
		return dao.selectThreeS();
	}
	
	public GachaContentDTO selectThreeA() {
		return dao.selectThreeA();
	}
	
	public GachaContentDTO selectThreeB() {
		return dao.selectThreeB();
	}
	
	public GachaContentDTO selectThreeC() {
		return dao.selectThreeC();
	}
	
	public GachaContentDTO selectTwoSS() {
		return dao.selectTwoSS();
	}
	
	public GachaContentDTO selectTwoS() {
		return dao.selectTwoS();
	}
	
	public GachaContentDTO selectTwoA() {
		return dao.selectTwoA();
	}
	
	public GachaContentDTO selectTwoB() {
		return dao.selectTwoB();
	}
	
	public GachaContentDTO selectTwoC() {
		return dao.selectTwoC();
	}
	
	public GachaContentDTO selectOneSS() {
		return dao.selectOneSS();
	}
	
	public GachaContentDTO selectOneS() {
		return dao.selectOneS();
	}
	
	public GachaContentDTO selectOneA() {
		return dao.selectOneA();
	}
	
	public GachaContentDTO selectOneB() {
		return dao.selectOneB();
	}
	
	public GachaContentDTO selectOneC() {
		return dao.selectOneC();
	}
}
