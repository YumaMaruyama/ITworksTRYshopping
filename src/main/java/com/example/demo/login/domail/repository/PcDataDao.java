package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PurchaseDTO;

public interface PcDataDao {

	public int insertOne(PcDataDTO pcdatadto);

	public List<PcDataDTO> selectMany();

	public PcDataDTO selectOne(int id);

	public int insertCheckSelectOne(PcDataDTO pcdatadto);

	public PcDataDTO selectPcName(int productId);

	public int updateOne(PurchaseDTO purchasedto, int productStock);
}
