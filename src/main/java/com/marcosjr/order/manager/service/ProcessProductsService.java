package com.marcosjr.order.manager.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.marcosjr.order.manager.model.Product;

@Component
public class ProcessProductsService {
	
	public double total(List<Product> products){
		return products.stream().mapToDouble(e -> e.getTotalPrice()).sum();
	}
	
}
