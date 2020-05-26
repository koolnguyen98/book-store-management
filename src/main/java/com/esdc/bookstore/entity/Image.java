package com.esdc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "image")
public class Image {

	@Id
    @GeneratedValue
    @Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "image", nullable = false)
	private String image;
	
	@Lob
	@Column(name = "base64", nullable = false)
	private String base64;
	
	@ManyToOne()
    @JoinColumn(name="product_id", nullable = false) 
	private Product product;

	public Image() {
		super();
	}

	public Image(String image) {
		super();
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
}
