package com.ooms.entity.bean;

public class CartBean {
	
	private int cartId;
	private int quantity;
	private double amount;
	private UserBean user;
	
	private String status;
	
	
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
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	@Override
	public String toString() {
		return "CartBean [cartId=" + cartId + ", quantity=" + quantity + ", amount=" + amount + ", user=" + user
				+ ", status=" + status + "]";
	}
	
	
	
	
	

}
 