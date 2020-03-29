package com.esdc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "stationery")
@DiscriminatorValue("2")
@PrimaryKeyJoinColumn(name="id")  
public class Stationery extends Product {
	
	@NotNull
	@Size(max = 50)
	@Column(name = "made_in", nullable = false)
	private String madeIn;
	
	@NotNull
	@Size(max = 500)
	@Column(name = "parameter", nullable = false)
	private String parameter;
	
	@ManyToOne()
    @JoinColumn(name="brand_id", nullable = false) 
	private Brand brand;

	public Stationery() {
		super();
	}

	public Stationery(@NotNull @Size(max = 50) String madeIn, @NotNull @Size(max = 500) String parameter) {
		super();
		this.madeIn = madeIn;
		this.parameter = parameter;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
}
