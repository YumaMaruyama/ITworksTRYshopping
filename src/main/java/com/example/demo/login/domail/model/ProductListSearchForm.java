package com.example.demo.login.domail.model;

import lombok.Data;

@Data
public class ProductListSearchForm {

	private String product;
	
	private String os;
	
	private int priceFrom;
	
	private int priceTo;
}
