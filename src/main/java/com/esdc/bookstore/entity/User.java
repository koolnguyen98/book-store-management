package com.esdc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
    @GeneratedValue
    @Column(name = "id")
	private Long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	@NotNull
	@Column(name = "sex", nullable = false, columnDefinition="tinyint(1) default 0")
	private boolean sex;
	
	@NotNull
	@Size(max = 120)
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@OneToOne(mappedBy = "user")
	private Account account;

	public User() {
		super();
	}

	public User(String fullName, boolean sex, String address, String email, String phoneNumber) {
		super();
		this.fullName = fullName;
		this.sex = sex;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", sex=" + sex + ", address=" + address + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", account=" + account + "]";
	}
	
}
