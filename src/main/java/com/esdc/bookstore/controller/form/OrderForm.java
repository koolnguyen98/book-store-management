package com.esdc.bookstore.controller.form;

public class OrderForm {
	private boolean paymentMethod;

	public OrderForm(boolean paymentMethod) {
		super();
		this.paymentMethod = paymentMethod;
	}

	public OrderForm() {
		super();
	}

	public boolean getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(boolean paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
