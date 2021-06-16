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
	@Email(groups = ValidGroup2.class)
	private String user_id;
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 6,max = 40,groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",groups = ValidGroup3.class)
	private String password;
    @NotBlank(groups = ValidGroup1.class)
	private String user_name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
}
