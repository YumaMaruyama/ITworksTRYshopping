package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CancelForm {

	private String title;
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min = 7, max = 7, groups = ValidGroup3.class)
	private String bankNumber;
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$",groups = ValidGroup2.class)
	@Length(min = 3, max = 3, groups = ValidGroup3.class)
	private String storeName;
	

}
