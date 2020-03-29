package com.esdc.bookstore.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookForm {
	
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
	
    private String translator;
	
	@NotNull
	private String formBookJacket;
	
	private Integer pageNumber;
	
	private Integer publishingYear;
	
	@NotNull
	private String language;
	
	private int author;

	private int publishingCompany;

	public BookForm() {
		super();
	}

	public BookForm(int id, @NotNull @Size(max = 50) String productName, @NotNull @Size(max = 100) String size,
			@NotNull @Size(max = 2000) String description, double discount, double price,
			boolean status, int amount, int productType, String translator, @NotNull String formBookJacket,
			Integer pageNumber, Integer publishingYear, @NotNull String language, int author, int publishingCompany) {
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
		this.translator = translator;
		this.formBookJacket = formBookJacket;
		this.pageNumber = pageNumber;
		this.publishingYear = publishingYear;
		this.language = language;
		this.author = author;
		this.publishingCompany = publishingCompany;
	}

	public BookForm(@NotNull @Size(max = 50) String productName, @NotNull @Size(max = 100) String size,
			@NotNull @Size(max = 2000) String description, double discount, double price, boolean status, int amount,
			int productType, String translator, @NotNull String formBookJacket, Integer pageNumber,
			Integer publishingYear, @NotNull String language, int author, int publishingCompany) {
		super();
		this.productName = productName;
		this.size = size;
		this.description = description;
		this.discount = discount;
		this.price = price;
		this.status = status;
		this.amount = amount;
		this.productType = productType;
		this.translator = translator;
		this.formBookJacket = formBookJacket;
		this.pageNumber = pageNumber;
		this.publishingYear = publishingYear;
		this.language = language;
		this.author = author;
		this.publishingCompany = publishingCompany;
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

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getFormBookJacket() {
		return formBookJacket;
	}

	public void setFormBookJacket(String formBookJacket) {
		this.formBookJacket = formBookJacket;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(Integer publishingYear) {
		this.publishingYear = publishingYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(int publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

}
