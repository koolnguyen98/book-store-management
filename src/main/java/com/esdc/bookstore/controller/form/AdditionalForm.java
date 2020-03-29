package com.esdc.bookstore.controller.form;

import javax.validation.constraints.NotNull;

public class AdditionalForm {

	private Integer id;
	
	@NotNull
	private String type;
	
	@NotNull
	private String name;
	
	@NotNull
	private String country;

	public AdditionalForm() {
		super();
	}

	public AdditionalForm(String type, @NotNull String name, @NotNull String country) {
		super();
		this.type = type;
		this.name = name;
		this.country = country;
	}

	public AdditionalForm(Integer id, String type, @NotNull String name, @NotNull String country) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
