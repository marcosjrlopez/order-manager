package com.marcosjr.order.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.marcosjr.order.manager.enums.OrderStatus;
import com.marcosjr.order.manager.exception.InvalidProductException;
import com.marcosjr.order.manager.exception.OrderDuplicateException;
import com.marcosjr.order.manager.exception.OrderWithoutTotalException;
import com.marcosjr.order.manager.exception.ProductListNotFoundException;
import com.marcosjr.order.manager.model.Order;
import com.marcosjr.order.manager.model.Product;
import com.marcosjr.order.manager.service.ValidateOrderService;

public class ValidateOrderTest {
	
	private static  ValidateOrderService validateOrderService;
	
	@BeforeAll
	public static void setup() {
		validateOrderService = new ValidateOrderService();
	}
	
	@Test
	public void shouldExistsProductList() throws InvalidProductException{
		try {
			Order orderSend = new Order();
			validateOrderService.validate(orderSend);
			Assert.fail();
		} catch (ProductListNotFoundException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateOrderService.MESSAGE_LIST_EMPTY));
		}
	}

	@Test
	public void shouldSaveOrderWithTotalOfProducts() throws OrderWithoutTotalException{
		try {
			Order orderSend = new Order();
			
			Product product = new Product();
			product.setIdProduto("15");
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			
			orderSend.setProducts(products);
			orderSend.setIdCustomer("10");
			
			validateOrderService.validate(orderSend);
			Assert.fail();
		} catch (OrderWithoutTotalException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateOrderService.MESSAGE_TOTAL_WITHOUT));
		}
	}

	@Test
	public void notShouldExistsDuplicateOrder() throws OrderDuplicateException {
	
		try {
			Order orderSend = new Order();
			Order orderDb = new Order();
			
			Product product = new Product();
			product.setIdProduto("15");
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			
			orderSend.setProducts(products);
			orderDb.setProducts(products);
			
			orderSend.setIdCustomer("10");
			orderDb.setIdCustomer("10");
			
			orderDb.setStatus(OrderStatus.FINISHED.getValue());
			
			orderSend.setTotalPrice(150);
			orderDb.setTotalPrice(150);
			
			orderSend.setRequestTime(LocalDate.now());
			orderDb.setRequestTime(LocalDate.now());
			
			validateOrderService.isDuplicate(orderSend, orderDb);
			Assert.fail();
		} catch (OrderDuplicateException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateOrderService.MESSAGE_DUPLICATE_ORDER));
		}
	}
	

}
