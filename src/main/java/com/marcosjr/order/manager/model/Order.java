package com.marcosjr.order.manager.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Document
public class Order {
	
	@Id
	private String id;
	
	@NotNull(message = "O Id do Cliente deve ser informado.")
	private String idCustomer;
	private LocalDate requestTime;
	private double totalPrice;
	private String status;
	private String message;
	
	@NotNull(message = "Deve existir produtos vinculados ao pedido.")
	private List<Product> products;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	
	public LocalDate getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(LocalDate requestTime) {
		this.requestTime = requestTime;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	
}
