package com.hiephk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiephk.model.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	List<Order> findByUserId(int userId);
}
