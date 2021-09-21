package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CartForm {

	private int id;

	private int user_id;

	private int product_id;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 1, max = 2, groups = ValidGroup3.class)
	private String product_count;
	
}
