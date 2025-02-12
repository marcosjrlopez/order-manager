package com.marcosjr.order.manager.model;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

@Document
public class Product {
	
	@NotNull(message = "Produto invalido. Deve-se informar o id do produto.")
	private String idProduto;
	
	@NotNull(message = "Produto invalido. Deve-se informar o preco unitario.")
	private float unitPrice;
	
	@NotNull(message = "Produto invalido. Deve-se informar a quantidade.")
	private int quantity;
	
	@NotNull(message = "Produto invalido. Deve-se informar o total do produto.")
	private float totalPrice;

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduto, quantity, totalPrice, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(idProduto, other.idProduto) && quantity == other.quantity
				&& Float.floatToIntBits(totalPrice) == Float.floatToIntBits(other.totalPrice)
				&& Float.floatToIntBits(unitPrice) == Float.floatToIntBits(other.unitPrice);
	}

	

}
