package com.hiephk.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.Order;
import com.hiephk.model.OrderDetail;
import com.hiephk.model.Product;
import com.hiephk.payload.request.OrderRequest;
import com.hiephk.payload.response.OrderResponse;
import com.hiephk.service.OrderDetailService;
import com.hiephk.service.OrderService;
import com.hiephk.service.ProductService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addToCart")
	public OrderResponse addToCart(@RequestBody OrderRequest request) {
		Optional<Order> optional = orderService.findByUserIdAndOrderStatus(request.getUserId(), "closed");
		Order order = new Order();
		if(optional.isEmpty()) {
			order = Order.builder()
					.userId(request.getUserId())
					.orderStatus("closed")
					.build();
			orderService.save(order);
			System.out.println(order.toString());
		}else {
			order = optional.get();
		}
		System.out.println(order.toString());
		
		Optional<OrderDetail> orderDetailOptional = orderDetailService.findByOrderIdAndProductId(order.getId(), request.getProductId());
		Product product = productService.findById(request.getProductId()).get();
		
		if(orderDetailOptional.isEmpty()) {
			orderDetailService.save(OrderDetail.builder()
					.productId(product.get_id())
					.orderId(order.getId())
					.price(product.getPrice())
					.quantity(1)
					.build());
		}else {
			OrderDetail orderDetail = orderDetailOptional.get();
			orderDetail.setQuantity(orderDetail.getQuantity() + 1);
			orderDetail.setPrice(orderDetail.getQuantity() * product.getPrice());
			orderDetailService.save(orderDetail);
		}
		
		
		return OrderResponse.builder()
				.userId(request.getUserId())
				.orderStatus("closed")
				.orderDetails(orderDetailService.findByOrderId(order.getId()))
				.build();
	}
}
