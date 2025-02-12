package com.marcosjr.order.manager;

import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.marcosjr.order.manager.exception.InvalidProductException;
import com.marcosjr.order.manager.exception.ProductListNotFoundException;
import com.marcosjr.order.manager.model.Product;
import com.marcosjr.order.manager.service.ValidateProductsService;

public class ValidateProductTest {
	
	private static  ValidateProductsService validateProductsService;
	
	@BeforeAll
	public static void setup() {
		validateProductsService = new ValidateProductsService();
	}
	
	@Test
	public void shouldExistsProductList() throws InvalidProductException{
		try {
			ArrayList<Product> orderProductList = new ArrayList<Product>();
			validateProductsService.validate(orderProductList);
			Assert.fail();
		} catch (ProductListNotFoundException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateProductsService.MESSAGE_LIST_EMPTY));
		}
	}
	
	@Test
	public void shouldExistsIdProduct() throws InvalidProductException{
		try {
			Product product = new Product();
			product.setQuantity(10);
			product.setUnitPrice(2.5f);
			product.setTotalPrice(25);
			ArrayList<Product> orderProductList = new ArrayList<Product>();
			orderProductList.add(product);
			
			validateProductsService.validate(orderProductList);
			Assert.fail();
		} catch (InvalidProductException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateProductsService.MESSAGE_ID_PRODUCT));
		}
	}
	
	@Test
	public void shouldExistsQuantity() throws InvalidProductException{
		try {
			Product product = new Product();
			product.setIdProduto("100");
			product.setUnitPrice(100);
			product.setTotalPrice(25);
			ArrayList<Product> orderProductList = new ArrayList<Product>();
			orderProductList.add(product);
			
			validateProductsService.validate(orderProductList);
			Assert.fail();
		} catch (InvalidProductException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateProductsService.MESSAGE_QUANTITY));
		}
	}
	
	@Test
	public void shouldExistsUnitPrice() throws InvalidProductException{
		try {
			Product product = new Product();
			product.setIdProduto("100");
			product.setQuantity(10);
			product.setTotalPrice(25);
			ArrayList<Product> orderProductList = new ArrayList<Product>();
			orderProductList.add(product);
			
			validateProductsService.validate(orderProductList);
			Assert.fail();
		} catch (InvalidProductException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateProductsService.MESSAGE_UNI_PRICE));
		}
	}
	
	@Test
	public void shouldExistsTotalPrice() throws InvalidProductException{
		try {
			Product product = new Product();
			product.setIdProduto("100");
			product.setQuantity(10);
			product.setUnitPrice(25);
			ArrayList<Product> orderProductList = new ArrayList<Product>();
			orderProductList.add(product);
			
			validateProductsService.validate(orderProductList);
			Assert.fail();
		} catch (InvalidProductException e) {
			Assert.assertThat(e.getMessage(), CoreMatchers.is(ValidateProductsService.MESSAGE_TOTAL_PRICE));
		}
	}

}
