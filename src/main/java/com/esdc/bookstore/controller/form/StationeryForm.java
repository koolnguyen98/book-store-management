package com.esdc.bookstore.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StationeryForm {
	
	private int id;
	
	@NotNull
	@Size(max = 50)
	private String productName;

	@NotNull
	@Size(max = 100)
	private String size;

	@NotNull
	@Size(max = 2000)
	private String description;

	private double discount;

	private double price;

	private boolean status;

	private int amount;

	private int productType;
	
	@NotNull
    private String madeIn;
	
	@NotNull
	private String parameter;
	
	private int brand;

	public StationeryForm() {
		super();
	}

	public StationeryForm(@NotNull @Size(max = 50) String productName, @NotNull @Size(max = 100) String size,
			@NotNull @Size(max = 2000) String description, double discount, double price, boolean status, int amount,
			int productType, @NotNull String madeIn, @NotNull String parameter, int brand) {
		super();
		this.productName = productName;
		this.size = size;
		this.description = description;
		this.discount = discount;
		this.price = price;
		this.status = status;
		this.amount = amount;
		this.productType = productType;
		this.madeIn = madeIn;
		this.parameter = parameter;
		this.brand = brand;
	}

	public StationeryForm(int id, @NotNull @Size(max = 50) String productName, @NotNull @Size(max = 100) String size,
			@NotNull @Size(max = 2000) String description, double discount, double price, boolean status, int amount,
			int productType, @NotNull String madeIn, @NotNull String parameter, int brand) {
		super();
		this.id = id;
		this.productName = productName;
		this.size = size;
		this.description = description;
		this.discount = discount;
		this.price = price;
		this.status = status;
		this.amount = amount;
		this.productType = productType;
		this.madeIn = madeIn;
		this.parameter = parameter;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getBrand() {
		return brand;
	}

	public void setBrand(int brand) {
		this.brand = brand;
	}

}
