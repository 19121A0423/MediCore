package com.admin.bean;

import java.util.List;

public class CartBean {
	
	private int cartId;
	private int quantity;
	private double amount;
	private String status;
	private UserBean user;
	
	private List<ProductBean> products;
	
	public List<ProductBean> getProducts() {
		return products;
	}
	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
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
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", quantity=" + quantity + ", amount=" + amount + ", status=" + status
				+ ", user=" + user + ", products=" + products + "]";
	}

	
	
	
	
	
	

}
 