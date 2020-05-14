package com.esdc.bookstore.controller.form;

public class OrderForm {
	
	private boolean paymentMethod;
	private String stress;

	public OrderForm(boolean paymentMethod, String stress) {
		super();
		this.paymentMethod = paymentMethod;
		this.stress = stress;
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

	public String getStress() {
		return stress;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}
	
}
