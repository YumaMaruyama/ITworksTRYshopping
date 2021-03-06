package com.example.demo.login.domail.repository;

import java.util.List;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.ProductListSearchForm;
import com.example.demo.login.domail.model.PurchaseDTO;

public interface PcDataDao {

	public int insertOne(PcDataDTO pcdatadto);

	public List<PcDataDTO> selectMany();

	public PcDataDTO selectOne(int id);

	public int insertCheckSelectOne(PcDataDTO pcdatadto);

	public PcDataDTO selectPcName(int productId);

	public int updateOne(PurchaseDTO purchasedto, int productStock);
	
	public PcDataDTO pcdataOne(int productId);
	
	public int productEditOne(int productId,PcDataForm form);
	
	public List<PcDataDTO>  searchProductSelectMany(ProductListSearchForm form);
	
	public List<PcDataDTO> stockOutProductSelectMany();
	
	public List<PcDataDTO> listingStopProductSelectMany();
	
	public int listingStopUpdateOne(int productId);
	
	public int listingRestartUpdateOne(int productId);
	
	public int productStockUpdate(int pcdataId,int productStock);
}
