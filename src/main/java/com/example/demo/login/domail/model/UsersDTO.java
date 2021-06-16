package com.example.demo.login.domail.model;

import java.util.Date;

import lombok.Data;
@Data
public class UsersDTO {

	private int id;

	private String user_id;

	private String password;

	private String user_name;

	private Date birthday;

	private String role;
}
