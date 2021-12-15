package com.example.demo.login.domail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domail.model.PcDataDTO;
import com.example.demo.login.domail.model.PcDataForm;
import com.example.demo.login.domail.model.ProductListSearchForm;
import com.example.demo.login.domail.model.PurchaseDTO;
import com.example.demo.login.domail.model.StockInputForm;
import com.example.demo.login.domail.repository.PcDataDao;

@Service
public class PcDataService {

	@Autowired
	PcDataDao dao;

	public int insertOne(PcDataDTO pcdatadto) {
		int rowNumber = dao.insertOne(pcdatadto);

		if (rowNumber > 0) {
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

	public PcDataDTO selectPcName(int productId) {
		return dao.selectPcName(productId);
	}

	public int updateOne(PurchaseDTO purchasedto, int productStock) {
		return dao.updateOne(purchasedto, productStock);
	}
	
	public PcDataDTO pcdataOne(int productId) {
		return dao.pcdataOne(productId);
	}
	
	public int productEditOne(int productId,PcDataForm form) {
		return dao.productEditOne(productId,form);
	}
	
	public List<PcDataDTO>  searchProductSelectMany(ProductListSearchForm form) {
		return dao.searchProductSelectMany(form);
	}

	public List<PcDataDTO> stockOutProductSelectMany() {
		return dao.stockOutProductSelectMany();
	}
	
	public List<PcDataDTO> listingStopProductSelectMany() {
		return dao.listingStopProductSelectMany();
	}
	
	public int listingStopUpdateOne(int productId) {
		return dao.listingStopUpdateOne(productId);
	}
	
	public int listingRestartUpdateOne(int productId) {
		return dao.listingRestartUpdateOne(productId);
	}
	
	public int productStockUpdate(int pcdataId,StockInputForm form) {
		return dao.productStockUpdate(pcdataId,form);
	}
} 
