package com.hiephk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiephk.model.OrderDetail;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrderId(int orderId);
	Optional<OrderDetail> findByOrderIdAndProductId(int orderId, int productId);
}
