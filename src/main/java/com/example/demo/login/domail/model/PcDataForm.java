package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min=1,max=2,groups = ValidGroup3.class)
	private String pc_size;
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min=1,max=7,groups = ValidGroup3.class)
	private String price;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=1,max=240,groups = ValidGroup2.class)
	private String detail;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min=1,max=2,groups = ValidGroup3.class)
	private String product_stock;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=10,max=200,groups = ValidGroup2.class)
	private String pcImg;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min=10,max=200,groups = ValidGroup2.class)
	private String pcImg2;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min=10,max=200,groups = ValidGroup2.class)
	private String pcImg3;

}
