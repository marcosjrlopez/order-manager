package com.marcosjr.order.manager.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcosjr.order.manager.enums.OrderStatus;
import com.marcosjr.order.manager.exception.OrderDuplicateException;
import com.marcosjr.order.manager.exception.OrderNotFoundException;
import com.marcosjr.order.manager.model.Order;
import com.marcosjr.order.manager.service.OrderService;
import com.marcosjr.order.manager.service.ProcessProductsService;
import com.marcosjr.order.manager.service.ProductService;
import com.marcosjr.order.manager.service.ValidateOrderService;
import com.marcosjr.order.manager.service.ValidateProductsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProcessProductsService processProductsService;
	
	@Autowired
	private ValidateProductsService validateProductsService;
	
	@Autowired
	private ValidateOrderService validateOrderService;

	public OrderService getService() {
		return this.service;
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public ProcessProductsService getProcessProductsService() {
		return processProductsService;
	}
	
	public ValidateProductsService getValidateProductsService() {
		return validateProductsService;
	}
	
	public ValidateOrderService getValidateOrderService() {
		return validateOrderService;
	}
	
	@GetMapping("/all")
	public List<Order> retriveAllOrders(){
		return getService().findAll();
	}
	
	@GetMapping("/finished")
	public List<Order> retriveFinishedOrders(){
		return getService().findByStatus("FINISHED");
	}
	
	@GetMapping("/processing")
	public List<Order> retriveProcessingOrders(){
		return getService().findByStatus("PROCESSING");
	}
	
	@GetMapping("/failed")
	public List<Order> retriveFailedOrders(){
		return getService().findByStatus("ERROR");
	}
	
	@GetMapping("/receive/{id}")
	public Order retriveOrdersById(@PathVariable String id){
		Optional<Order> result = getService().findById(id);
		if(result.isEmpty())
			throw new OrderNotFoundException("Pedido nao encontrado.");
			
		return result.get();
	}
	
	@PostMapping("/receive")
	public ResponseEntity<Order> processOrder(@Valid @RequestBody Order order){
		try {
			order.setStatus(OrderStatus.PROCESSING.getValue());
			order.setRequestTime(LocalDate.now());
			
			getValidateProductsService().validate(order.getProducts());				
			order.setTotalPrice(getProcessProductsService().total(order.getProducts()));
			
			List<Order> ordersValidate = getService()
					.findByIdCustomer(order.getIdCustomer());
			
			for (Order orderDb : ordersValidate) {
				if(getValidateOrderService().isSameDate(order, orderDb))
					getValidateOrderService().isDuplicate(order, orderDb);
			}
			
			getValidateOrderService().validate(order);
			order.setStatus(OrderStatus.FINISHED.getValue());
			
		} catch (Exception e) {
			order.setStatus(OrderStatus.ERROR.getValue());
			order.setMessage(e.getMessage());
		}
	
		Order salvedOrder = getService().save(order);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(salvedOrder.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
		
}
