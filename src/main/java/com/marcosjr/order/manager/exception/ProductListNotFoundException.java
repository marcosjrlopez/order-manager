package com.marcosjr.order.manager.exception;

public class ProductListNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ProductListNotFoundException(String message) {
		super(message);
	}
}
