package com.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "composition")
public class Composition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "composition_id")
	private Integer compositionId;
	
	@Column(name="composition_name")
	private String compositionName;
	
	public Composition() {
	}

	public Composition(Integer compositionId, String compositionName) {
		this.compositionId = compositionId;
		this.compositionName = compositionName;
	}

	public Integer getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(Integer compositionId) {
		this.compositionId = compositionId;
	}

	public String getCompositionName() {
		return compositionName;
	}

	public void setCompositionName(String compositionName) {
		this.compositionName = compositionName;
	}

	@Override
	public String toString() {
		return "Composition [compositionId=" + compositionId + ", compositionName=" + compositionName + "]";
	}
	
}
