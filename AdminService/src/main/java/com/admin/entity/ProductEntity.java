package com.admin.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "category_id")
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="cart_id",referencedColumnName = "cart_id")
	private CartEntity cartEntity;
	
	public ProductEntity() {
		
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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public CartEntity getCartEntity() {
		return cartEntity;
	}

	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", description=" + description + ", category=" + category + ", cartEntity=" + cartEntity + "]";
	}

	
		
	

}
