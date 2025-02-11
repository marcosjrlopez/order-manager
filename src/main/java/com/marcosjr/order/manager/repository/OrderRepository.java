package com.marcosjr.order.manager.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marcosjr.order.manager.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	
	List<Order> findByStatus(String status);
	Optional<Order> findByIdOrIdCustomer(String id, String customer);
	List<Order> findByIdCustomer(String id);
	Optional<Order> findByIdCustomerAndRequestTime(String customer, Date requestTime);

}
