package com.example.demo.login.domail.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ProductListSearchForm {

	@Length(max = 30, groups = ValidGroup3.class)
	private String product;
	
	@Pattern(regexp = "[a-zA-Z]*", groups = ValidGroup2.class)
	@Length(max = 15, groups = ValidGroup3.class)
	private String os;
	
	@Pattern(regexp = "[0-9]*", groups = ValidGroup2.class)
	@Length(max = 9, groups = ValidGroup3.class)
	private String priceFrom;
	
	@Pattern(regexp = "[0-9]*", groups = ValidGroup2.class)
	@Length(max = 9, groups = ValidGroup3.class)
	private String priceTo;
}
