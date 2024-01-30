package com.admin.bean;

public class Cart {

	private Integer cartId;
	private Integer userId;
	private Integer productId;
	private Integer quantity;
	private Double amount;

	public Cart() {
		super();
	}

	public Cart(Integer cartId, Integer userId, Integer productId, Integer quantity, Double amount) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", productId=" + productId + ", quantity=" + quantity
				+ ", amount=" + amount + "]";
	}

}
