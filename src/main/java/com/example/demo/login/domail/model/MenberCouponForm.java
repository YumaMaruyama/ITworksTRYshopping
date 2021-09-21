package com.example.demo.login.domail.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MenberCouponForm {

	private int id;

	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 30, groups = ValidGroup2.class)
	private String title;

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 1, max = 2, groups = ValidGroup3.class)
	private String discount;

	private int couponRank;

	private Date registration_date;

}
