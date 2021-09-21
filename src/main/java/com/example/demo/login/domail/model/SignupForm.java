package com.example.demo.login.domail.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 80, groups = ValidGroup2.class)
	@Email(groups = ValidGroup3.class)
	private String user_id;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 8, max = 80, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup3.class)
	private String password;

	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 30, groups = ValidGroup2.class)
	private String user_name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@NotBlank(groups = ValidGroup1.class)
	@Length(max = 100, groups = ValidGroup2.class)
	private String address;
}
