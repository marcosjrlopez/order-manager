package com.marcosjr.order.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcosjr.order.manager.model.Product;
import com.marcosjr.order.manager.repository.ProductRepository;

@Component
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public ProductRepository getRepository() {
		return this.repository;
	}
	
	public List<Product> findAll(){
		return this.getRepository().findAll();
	}
	
	public List<Product> saveAll(List<Product> products){
		List<Product> saveProducts = new ArrayList<Product>();
		for (Product product : products) {
			saveProducts.add(getRepository().save(product));				
		}
		return saveProducts;
	}
	
	public Product save(Product product){
		return this.getRepository().save(product);
	}

}
