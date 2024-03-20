package com.admin.bean;

import java.util.List;

public class CartBean {
	
	private Integer cartId;
	private Integer quantity;
	private Double amount;
	private String status;
	private UserBean user;
	
	private List<ProductBean> products;
	
	public List<ProductBean> getProducts() {
		return products;
	}
	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
 