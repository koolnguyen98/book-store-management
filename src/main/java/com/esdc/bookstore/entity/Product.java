package com.esdc.bookstore.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {
	
	@Id
    @GeneratedValue
    @Column(name = "id")
	private Integer id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "size", nullable = false)
	private String size;
	
	@NotNull
	@Size(max = 2000)
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "discount", nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
	private double discount;
	
	@NotNull
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "date_create", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp dateCreate;
	
	@NotNull
	@Column(name = "status", nullable = false, columnDefinition="tinyint(1) default 0")
	private boolean status;

	@NotNull
	@Column(name = "amount", nullable = false, columnDefinition="integer default 0")
	private int amount;
	
	@ManyToOne()
    @JoinColumn(name="product_type_id", nullable = false) 
	private ProductType productType;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Image> images = new ArrayList<Image>();
	
	@OneToMany(targetEntity=ShoppingCart.class, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();

	@OneToMany(targetEntity=OrderDetail.class, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	public Product() {
		super();
	}

	public Product(String productName, String size, String description, double discount, double price, boolean status, int amount) {
		super();
		this.productName = productName;
		this.size = size;
		this.description = description;
		this.discount = discount;
		this.price = price;
		this.status = status;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public boolean isStatus() {
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

	@JsonIgnore
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@JsonIgnore
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public Product addImage(Image image) {
		if(this.images == null) {
			this.images = new ArrayList<Image>();
		}
		this.images.add(image);
		return this;
	}

	@JsonIgnore
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCarts;
	}

	public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}
	
	public Product addShoppingCart(ShoppingCart shoppingCart) {
		if(this.shoppingCarts == null) {
			this.shoppingCarts = new ArrayList<ShoppingCart>();
		}
		this.shoppingCarts.add(shoppingCart);
		return this;
	}

	@JsonIgnore
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public Product addOrderDetail(OrderDetail orderDetail) {
		if(this.orderDetails == null) {
			this.orderDetails = new ArrayList<OrderDetail>();
		}
		this.orderDetails.add(orderDetail);
		return this;
	}
	
}
