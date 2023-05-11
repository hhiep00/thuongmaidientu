package com.hiephk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.Order;
import com.hiephk.payload.request.OrderRequest;
import com.hiephk.payload.response.OrderResponse;
import com.hiephk.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("")
	public Order createOrder(@RequestBody OrderRequest request) {
		try {
			return orderService.createOrder(request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/{orderId}")
	public OrderResponse getOneOrder(@PathVariable int orderId) {
		try {
			return orderService.getOrderById(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/orders-user")
	public List<OrderResponse> getAllOrder() {
		try {
			return orderService.getAllOrder();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
