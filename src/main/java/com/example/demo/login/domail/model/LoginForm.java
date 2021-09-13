package com.example.demo.login.domail.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class LoginForm {

	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 80,groups = ValidGroup2.class)
	@Email(groups = ValidGroup3.class)
	private String user_id;
	
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 8,max = 80,groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",groups = ValidGroup3.class)
	private String password;
}
