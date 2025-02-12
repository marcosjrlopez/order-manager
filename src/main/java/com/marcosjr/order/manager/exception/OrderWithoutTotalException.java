package com.marcosjr.order.manager.exception;

public class OrderWithoutTotalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderWithoutTotalException(String message) {
		super(message);
	}
}
