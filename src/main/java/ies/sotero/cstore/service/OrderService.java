package ies.sotero.cstore.service;

import java.util.List;

import ies.sotero.cstore.model.Order;

public interface OrderService {
	List<Order> findAll();
	
	Order save(Order order);
	
	String generateOrderNumber();
}
