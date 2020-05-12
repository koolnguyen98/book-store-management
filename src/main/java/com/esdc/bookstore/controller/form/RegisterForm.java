package com.esdc.bookstore.controller.form;

import javax.validation.constraints.NotNull;

public class RegisterForm {
	private int id;

	@NotNull
	private String username;

	@NotNull
	private String password;

	@NotNull
	private String confPassword;

	@NotNull
	private String fullName;

	@NotNull
	private String email;

	@NotNull
	private String phoneNumber;
	
	@NotNull
	private String address;

	@NotNull
    private boolean sex;
	
	public RegisterForm() {}
	
	public RegisterForm(@NotNull String username, @NotNull String password, @NotNull String confPassword,
			@NotNull String fullName, @NotNull String email, @NotNull String phoneNumber, @NotNull String address,
			@NotNull boolean sex) {
		super();
		this.username = username;
		this.password = password;
		this.confPassword = confPassword;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.sex = sex;
	}

	public RegisterForm(int id, @NotNull String username, @NotNull String password, @NotNull String confPassword,
			@NotNull String fullName, @NotNull String email, @NotNull String phoneNumber, @NotNull String address,
			@NotNull boolean sex) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.confPassword = confPassword;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
}
