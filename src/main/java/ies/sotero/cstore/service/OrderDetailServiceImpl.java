package ies.sotero.cstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.sotero.cstore.model.OrderDetail;
import ies.sotero.cstore.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	}

}
