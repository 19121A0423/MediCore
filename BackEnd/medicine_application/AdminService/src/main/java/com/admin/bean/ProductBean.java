package com.admin.bean;

import java.util.List;

import com.admin.entity.Category;
import com.admin.entity.Composition;

public class ProductBean {
	
	private Integer productId;
	private String name;
	private Double price;
	private Integer quantity;
	private String description;
	private Integer quantityProduct;
	private String image;
	private Category category;
	private String status;
	private List<Composition> compositions;
	
	public ProductBean() {

	}

	
	


	public List<Composition> getCompositions() {
		return compositions;
	}





	public void setCompositions(List<Composition> compositions) {
		this.compositions = compositions;
	}





	public ProductBean(Integer productId, String name, Double price, Integer quantity, String description,
			Integer quantityProduct, String image, Category category, String status,
			List<Composition> compositions) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.quantityProduct = quantityProduct;
		this.image = image;
		this.category = category;
		this.status = status;
		this.compositions = compositions;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	public Integer getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", description=" + description + ", quantityProduct=" + quantityProduct + ", image=" + image
				+ ", category=" + category + ", status=" + status + ", compositions=" + compositions + "]";
	}


	

	

	
	

}