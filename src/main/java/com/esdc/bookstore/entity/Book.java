package com.esdc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
@PrimaryKeyJoinColumn(name="id")  
public class Book extends Product{
	
	@Size(max = 50)
	@Column(name = "translator")
	private String translator;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "form_bookjacket", nullable = false)
	private String formBookJacket;
	
	@Column(name = "page_number", nullable = false, columnDefinition="integer default 1")
	private Integer pageNumber;
	
	@Column(name = "publishing_year", nullable = false, columnDefinition="integer default 0000")
	private Integer publishingYear;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "language", nullable = false)
	private String language;
	
	@ManyToOne()
    @JoinColumn(name="author_id", nullable = false) 
	private Author author;
	
	@ManyToOne()
    @JoinColumn(name="publishing_company_id", nullable = false) 
	private PublishingCompany publishingCompany;
	
	
	public Book() {
		super();
	}

	public Book(@Size(max = 50) String translator, @NotNull @Size(max = 50) String formBookJacket, Integer pageNumber,
			Integer publishingYear, @NotNull @Size(max = 50) String language) {
		super();
		this.translator = translator;
		this.formBookJacket = formBookJacket;
		this.pageNumber = pageNumber;
		this.publishingYear = publishingYear;
		this.language = language;
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
	
}
