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
@Table(name = "shopping_cart")
public class ShoppingCart {
	
	@EmbeddedId
	private ShoppingCartKey shoppingCartKey;
	
	@ManyToOne(targetEntity=Account.class, fetch = FetchType.LAZY)
    @MapsId("account_id")
	@JoinColumn(name = "account_id")
    private Account account;
 
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
	@Column(name = "total_price", nullable = false)
	private double totalPrice;

	public ShoppingCart() {
		super();
	}

	public ShoppingCart(ShoppingCartKey shoppingCartKey, int amount, double unitPrice, double totalPrice) {
		super();
		this.shoppingCartKey = shoppingCartKey;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	public ShoppingCartKey getShoppingCartKey() {
		return shoppingCartKey;
	}

	public void setShoppingCartKey(ShoppingCartKey shoppingCartKey) {
		this.shoppingCartKey = shoppingCartKey;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
