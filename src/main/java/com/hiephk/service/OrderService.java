package com.hiephk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Order;
import com.hiephk.repository.OrderRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;

	public <S extends Order> S save(S entity) {
		return orderRepo.save(entity);
	}

	public Optional<Order> findByUserIdAndOrderStatus(int userId, String orderStatus) {
		return orderRepo.findByUserIdAndOrderStatus(userId, orderStatus);
	}
	
	
}
