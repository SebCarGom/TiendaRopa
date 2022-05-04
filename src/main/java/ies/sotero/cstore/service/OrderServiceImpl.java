package ies.sotero.cstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.sotero.cstore.model.Order;
import ies.sotero.cstore.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public String generateOrderNumber() {
		int num = 0;
		
		String numConcat = "";
		
		List<Order> orders = findAll();
		
		List<Integer> nums = new ArrayList<>();

		orders.stream().forEach(o -> nums.add(Integer.parseInt(o.getNumber())));
		
		if(orders.isEmpty()) {
			num = 1;
		} else {
			num = nums.stream().max(Integer::compare).get();
			
			num++;
		}
		
		if(num < 10) {
			numConcat = "000000000" + String.valueOf(num);
		} else if (num < 100) {
			numConcat = "00000000" + String.valueOf(num);
		} else if (num < 1000) {
			numConcat = "0000000" + String.valueOf(num);
		} else if (num < 10000) {
			numConcat = "000000" + String.valueOf(num);
		}
		
		return numConcat;
	}
	
}
