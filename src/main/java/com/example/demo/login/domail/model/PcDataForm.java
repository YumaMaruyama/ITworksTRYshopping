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

	@NotBlank
	@Length(min=1,max=15)
	private String company;

	@NotBlank
	@Length(min=3,max=9)
	private String os;

	@NotBlank
	@Length(min=1,max=30)
	private String pc_name;

	@NotNull
	@Min(1)
	@Max(3) 
	private int pc_size;
	
	@NotNull
	@Min(4)
	@Max(9) 
	private int price;

	@NotBlank
	@Length(min=10,max=240)
	private String detail;

	@NotNull
	@Min(1)
	@Max(2) 
	private int product_stock;

	@NotBlank
	@Length(min=30,max=200)
	private String pcImg;

	@NotBlank
	@Length(min=30,max=200)
	private String pcImg2;
	
	@NotBlank
	@Length(min=30,max=200)
	private String pcImg3;

}
