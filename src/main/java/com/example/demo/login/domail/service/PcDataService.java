package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.repository.PcDataDao;

@Service
public class PcDataService {

	@Autowired
	PcDataDao dao;

	public int insertOne(PcDataDTO pcdatadto) {
		int rowNumber = dao.insertOne(pcdatadto);

		if(rowNumber > 0) {
			System.out.println("insert成功");
		}

		return rowNumber;
	}

	public List<PcDataDTO> selectMany() {

	return dao.selectMany();
	}

	public PcDataDTO selectOne(int id) {

		return dao.selectOne(id);

	}

	public int insertCheckSelectOne(PcDataDTO pcdatadto) {
		return dao.insertCheckSelectOne(pcdatadto);

	}


}
