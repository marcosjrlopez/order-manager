package com.marcosjr.order.manager.service;

import org.springframework.stereotype.Component;

import com.marcosjr.order.manager.enums.OrderStatus;
import com.marcosjr.order.manager.exception.OrderDuplicateException;
import com.marcosjr.order.manager.exception.OrderWithoutTotalException;
import com.marcosjr.order.manager.exception.ProductListNotFoundException;
import com.marcosjr.order.manager.model.Order;
import com.marcosjr.order.manager.model.Product;

@Component
public class ValidateOrderService {
	
	public static final String MESSAGE_LIST_EMPTY       =   "Lista de produtos deve conter itens.";
	public static final String MESSAGE_DUPLICATE_ORDER  =   "Pedido ja processado.";
	public static final String MESSAGE_TOTAL_WITHOUT    =   "Pedido sem valor total.";

	public void isDuplicate(Order orderSend, Order orderDb){
		startValidation(orderSend);
		
		if(orderDb == null) return;
		if(!orderDb.getStatus().equals(OrderStatus.FINISHED.getValue())) return;
		
		boolean isSameCustommer = orderDb.getIdCustomer().equals(orderSend.getIdCustomer());
		boolean isSameProducts = isSameProducts(orderSend, orderDb);
		boolean isSameDate = isSameDate(orderSend, orderDb);	
		
		if(isSameCustommer && isSameProducts && isSameDate) 
			throw new OrderDuplicateException(MESSAGE_DUPLICATE_ORDER);	
		
	}

	private void startValidation(Order orderSend) {
		if(orderSend == null || orderSend.getProducts() == null || orderSend.getProducts().isEmpty())
			 throw new ProductListNotFoundException(MESSAGE_LIST_EMPTY);
	}
	
	public void validate(Order orderSend){
		startValidation(orderSend);
			
		if(orderSend.getTotalPrice() <= 0.0)  
			throw new OrderWithoutTotalException(MESSAGE_TOTAL_WITHOUT);
		
	}
	
	private boolean isSameProducts(Order orderSend, Order orderDb) {
		int find = 0;
		for (Product product : orderSend.getProducts()) {
			for (Product productb : orderDb.getProducts()) {
				
				if(product.getIdProduto().equals(productb.getIdProduto())){
					find++;
					continue;
				}	
			}
		}
		
		return orderDb.getProducts().size() == find;
	}
	
	public boolean isSameDate(Order orderSend, Order orderDb) {
		return orderSend.getRequestTime().getDayOfMonth() == orderDb.getRequestTime().getDayOfMonth() &&
				orderSend.getRequestTime().getMonth() == orderDb.getRequestTime().getMonth() &&
				orderSend.getRequestTime().getYear() == orderDb.getRequestTime().getYear();
	}
}
