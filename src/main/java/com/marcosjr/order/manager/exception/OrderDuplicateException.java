package com.marcosjr.order.manager.exception;

public class OrderDuplicateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OrderDuplicateException(String message) {
		super(message);
	}
}
