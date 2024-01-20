package com.order.bean;

public class Cart {

	private int cartId;
	private int userId;
	private int productId;
	private int quantity;
	private double amount;

	public Cart() {
		super();
	}

	public Cart(int cartId, int userId, int productId, int quantity, double amount) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", productId=" + productId + ", quantity=" + quantity
				+ ", amount=" + amount + "]";
	}

}
