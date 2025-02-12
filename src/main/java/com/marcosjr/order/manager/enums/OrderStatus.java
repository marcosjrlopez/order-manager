package com.marcosjr.order.manager.enums;

public enum OrderStatus {
	PROCESSING("PROCESSING"),
	FINISHED("FINISHED"),
	ERROR("ERROR");

	private String descricao;
	
	OrderStatus(String desc) {
		this.descricao = desc;
	}
	
	public String getValue() {
		return descricao;
	}
}
