package com.admin.bean;

public class CompositionBean {

	private Integer compositionId;
	private String compositionName;
	
	public CompositionBean() {
		super();
	}

	public CompositionBean(Integer compositionId, String compositionName) {
		super();
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
