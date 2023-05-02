package com.hiephk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Order;
import com.hiephk.model.OrderDetail;
import com.hiephk.repository.OrderDetailRepo;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailRepo orderDetailRepo;

	public List<OrderDetail> findByOrderId(int orderId) {
		return orderDetailRepo.findByOrderId(orderId);
	}

	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepo.save(entity);
	}

	public Optional<OrderDetail> findByOrderIdAndProductId(int orderId, int productId) {
		return orderDetailRepo.findByOrderIdAndProductId(orderId, productId);
	}
	
	
}
