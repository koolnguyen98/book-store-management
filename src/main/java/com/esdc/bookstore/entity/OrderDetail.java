package com.esdc.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	@EmbeddedId
	private OrderDetailKey orderDetailKey;
	
	@ManyToOne(targetEntity=Order.class, fetch = FetchType.LAZY)
    @MapsId("order_id")
	@JoinColumn(name = "order_id")
    private Order order;
 
    @ManyToOne(targetEntity=Product.class, fetch = FetchType.LAZY)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;
	
    @NotNull
	@Column(name = "amount", nullable = false)
	private int amount;
	
    @NotNull
	@Column(name = "unit_price", nullable = false)
	private double unitPrice;
    
    @NotNull
	@Column(name = "discount", nullable = false)
	private double discount;
	
    @NotNull
	@Column(name = "total_price", nullable = false)
	private double totalPrice;

	public OrderDetail() {
		super();
	}

	public OrderDetail(OrderDetailKey orderDetailKey, int amount, double unitPrice, double totalPrice) {
		super();
		this.orderDetailKey = orderDetailKey;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	public OrderDetailKey getOrderDetailKey() {
		return orderDetailKey;
	}

	public void setOrderDetailKey(OrderDetailKey orderDetailKey) {
		this.orderDetailKey = orderDetailKey;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	
	
}
