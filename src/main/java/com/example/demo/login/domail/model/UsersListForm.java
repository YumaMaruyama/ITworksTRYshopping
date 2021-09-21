package com.example.demo.login.domail.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UsersListForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 30, groups = ValidGroup2.class)
	private String userName;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 80, groups = ValidGroup2.class)
	private String address;
}
