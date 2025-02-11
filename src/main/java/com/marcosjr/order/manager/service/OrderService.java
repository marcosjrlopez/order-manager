package com.marcosjr.order.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marcosjr.order.manager.model.Order;
import com.marcosjr.order.manager.repository.OrderRepository;

@Component
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public OrderRepository getRepository() {
		return this.repository;
	}
	
	public List<Order> findAll(){
		return this.getRepository().findAll();
	}
	
	public Optional<Order> findById(String id){
		return this.getRepository().findById(id);
	}
	
	public List<Order> findByStatus(String status){
		return this.getRepository().findByStatus(status);
	}
	
	public Optional<Order> findByIdOrIdCustomer(String id, String customer){
		return this.getRepository().findByIdOrIdCustomer(id, customer);
	}
	
	public List<Order> findByIdCustomer(String idCustomer){
		return this.getRepository().findByIdCustomer(idCustomer);
	}

	public Optional<Order> findByIdCustomerAndRequestTime(String customer, Date requestTime){
		return this.getRepository().findByIdCustomerAndRequestTime(customer, requestTime);
	}
	
	public Order save(Order order){
		return this.getRepository().save(order);
	}

}
