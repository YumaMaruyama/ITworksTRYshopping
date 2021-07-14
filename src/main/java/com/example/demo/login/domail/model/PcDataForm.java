package com.example.demo.login.domail.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PcDataForm {
	
	private int id;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=1,max=15,groups = ValidGroup2.class)
	private String company;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=3,max=9,groups = ValidGroup2.class)
	private String os;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=1,max=30,groups = ValidGroup2.class)
	private String pc_name;

	@NotNull(groups = ValidGroup1.class)
	//@NumberFormat(pattern = "\\d{3}")
	//@Size(min = 1,max = 3,groups = ValidGroup2.class)
	//@Size(max = 3,groups = ValidGroup2.class)
//	@Min(1)
//	@Max(3) 
	private Integer pc_size;
	
	@NotNull(groups = ValidGroup1.class)
	@Min(4)
	@Max(9) 
	private Integer price;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=10,max=240,groups = ValidGroup2.class)
	private String detail;

	@NotNull(groups = ValidGroup1.class)
	@Min(1)
	@Max(2) 
	private Integer product_stock;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=30,max=200,groups = ValidGroup2.class)
	private String pcImg;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=30,max=200,groups = ValidGroup2.class)
	private String pcImg2;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min=30,max=200,groups = ValidGroup2.class)
	private String pcImg3;

}
