package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.PcDataDTO;

public interface PcDataDao {

	public int insertOne(PcDataDTO pcdatadto);

	public List<PcDataDTO> selectMany();

	public PcDataDTO selectOne(int id);
}
