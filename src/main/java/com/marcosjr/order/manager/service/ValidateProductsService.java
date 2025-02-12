package com.marcosjr.order.manager.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.marcosjr.order.manager.exception.InvalidProductException;
import com.marcosjr.order.manager.exception.ProductListNotFoundException;
import com.marcosjr.order.manager.model.Product;

@Component
public class ValidateProductsService {
	
	public static final String MESSAGE_ID_PRODUCT =   "Produto invalido. Campo idProduto deve ser informado.";
	public static final String MESSAGE_QUANTITY =     "Produto invalido. Campo quantity deve ser informado.";
	public static final String MESSAGE_UNI_PRICE =    "Produto invalido. Campo unitPrice deve ser informado.";
	public static final String MESSAGE_TOTAL_PRICE =  "Produto invalido. Campo totalPrice deve ser informado.";
	public static final String MESSAGE_LIST_EMPTY =   "Lista de produtos deve conter itens.";

	
	public void validate(List<Product> products){
		String message = null;
		
		if(products == null || products.isEmpty())
			 throw new ProductListNotFoundException(MESSAGE_LIST_EMPTY);
		
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getIdProduto() == null || products.get(i).getIdProduto().trim() == "") {
				products.get(i).setIdProduto("0");
				message = MESSAGE_ID_PRODUCT;
			}
			
			if(products.get(i).getQuantity() <= 0) 
				message = MESSAGE_QUANTITY;
	
			
			if(products.get(i).getUnitPrice() <= 0.0) 
				message = MESSAGE_UNI_PRICE;
			
			
			if(products.get(i).getTotalPrice() <= 0.0) 
				message = MESSAGE_TOTAL_PRICE;
		
			
			if(message != null)
				throw new InvalidProductException(message);
		}
	}
	
}
