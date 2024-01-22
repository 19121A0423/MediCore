package com.admin.bean;



public class Product {
	private Integer productId;
	private String name;
	private Double price;
	private Integer quantity;
	private String description;
	private Integer categoryId;
	public Product() {

	}

	public Product(Integer productId, String name, Double price, Integer quantity, String description,Integer categoryId) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.categoryId=categoryId;
			}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", description=" + description +"categoryId"+categoryId+"]";
	}


}
