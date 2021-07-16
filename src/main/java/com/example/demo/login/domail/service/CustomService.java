package com.example.demo.login.domail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.PcDetailDataDTO;
import com.example.demo.login.domail.repository.CustomDao;

@Service
public class CustomService {

	@Autowired
	CustomDao dao;

	public int UpdateOne(int id, int select_id, String memory, String hardDisc, String cpu, int customPrice) {
		return dao.UpdateOne(id, select_id, memory, hardDisc, cpu, customPrice);
	}

	public PcDetailDataDTO selectOne(int id, int select_id) {
		return dao.selectOne(id, select_id);
	}

	public int selectCustomProduct_id(int id, int select_id) {
		return dao.selectCustomProduct_id(id, select_id);
	}

	public int insertCustomData(int id, int select_id, String defaultMemory, String defaultHardDisc,
			String defaultCpu,int customPrice) {
		return dao.insertCustomData(id, select_id, defaultMemory, defaultHardDisc, defaultCpu,customPrice);
	}
	
	public int deleteCustomOne(int id,int getId) {
		return dao.deleteCustomOne(id,getId);
	}
	public int selectCustomId(int purchaseId) {
		return dao.selectCustomId(purchaseId);
	}
}
