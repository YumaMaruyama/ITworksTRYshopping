package com.example.demo.login.domail.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CouponForm {

	private int id;
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 1, max = 2, groups = ValidGroup3.class)
	private String discount;
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 1, max = 3, groups = ValidGroup3.class)
	private String purchaseCountTarget;
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "[0-9]+$", groups = ValidGroup2.class)
	@Length(min = 1, max = 7, groups = ValidGroup3.class)
	private String purchaseTotalPriceTarget;
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 30, groups = ValidGroup2.class)
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;

}
