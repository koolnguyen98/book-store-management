package com.esdc.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingCartKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750959942526564436L;

	@Column(name = "account_id")
	private int accountId;

	@Column(name = "product_id")
	private int productId;

	public ShoppingCartKey(int accountId, int productId) {
		super();
		this.accountId = accountId;
		this.productId = productId;
	}

	public ShoppingCartKey() {
		super();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartKey other = (ShoppingCartKey) obj;
		if (accountId != other.accountId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}
	
}
