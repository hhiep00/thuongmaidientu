package com.hiephk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hiephk.model.Address;
import com.hiephk.model.Order;
import com.hiephk.model.OrderDetail;
import com.hiephk.model.Product;
import com.hiephk.payload.request.OrderRequest;
import com.hiephk.payload.request.ProductOrderRequest;
import com.hiephk.payload.response.OrderResponse;
import com.hiephk.payload.response.ProductDetailResponse;
import com.hiephk.repository.AddressRepo;
import com.hiephk.repository.OrderDetailRepo;
import com.hiephk.repository.OrderRepo;
import com.hiephk.repository.ProductRepo;
import com.hiephk.repository.UserRepo;

@Service
public class OrderService {
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private OrderDetailRepo orderDetailRepo;
	@Autowired
	private ProductRepo productRepo;

	public <S extends Order> S save(S entity) {
		return orderRepo.save(entity);
	}
	public Order createOrder(OrderRequest request) {
		Object email = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userRepo.findByEmail(email.toString()).get().get_id();
		
		Order order = Order.builder()
				.userId(userId)
				.totalPrice(request.getTotalPrice())
				.createdAt(new Date())
				.updatedAt(new Date())
				.build();
		orderRepo.save(order);
		
		addressRepo.save(Address.builder()
				.orderId(order.get_id())
				.address(request.getShippingAddress().getAddress())
				.city(request.getShippingAddress().getCity())
				.postalCode(request.getShippingAddress().getPostalCode())
				.country(request.getShippingAddress().getCountry())
				.build());
		
		for(ProductOrderRequest productOrderRequest:request.getCartItems()) {
			orderDetailRepo.save(OrderDetail.builder()
					.productId(productOrderRequest.get_id())
					.quantity(productOrderRequest.getQty())
					.orderId(order.get_id())
					.build());
			
		}
		
		return order;
	}
	public OrderResponse getOrderById(int orderId) {
		OrderResponse orderResponse = new OrderResponse();
		Order order = orderRepo.findById(orderId).get();
		int total = 0;
		List<ProductDetailResponse> productDetailResponses = new ArrayList<>();
		
		for(OrderDetail orderDetail : orderDetailRepo.findByOrderId(orderId)) {
			Product product = productRepo.findById(orderDetail.getProductId()).get();
			
			ProductDetailResponse productDetailResponse = new ProductDetailResponse();
			productDetailResponse.set_id(product.get_id());
			productDetailResponse.setQty(orderDetail.getQuantity());
			productDetailResponse.setImage(product.getImage());
			productDetailResponse.setName(product.getBrand());
			productDetailResponse.setPrice(product.getPrice());
			
			productDetailResponses.add(productDetailResponse);
			
			total += orderDetail.getQuantity() * product.getPrice();
		}
		
		orderResponse.setUser(order.getUserId());
		orderResponse.setCreatedAt(order.getCreatedAt());
		orderResponse.setUpdatedAt(order.getUpdatedAt());
		orderResponse.set__v(0);
		orderResponse.setIsPaid(true);
		orderResponse.setTotalPrice(order.getTotalPrice());
		orderResponse.set_id(orderId);
		orderResponse.setShippingAddress(addressRepo.findByOrderId(orderId));
		orderResponse.setCartItems(productDetailResponses);
		
		System.out.println(total);
		return orderResponse;
	}
	
	public List<OrderResponse> getAllOrder() {
		Object email = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userRepo.findByEmail(email.toString()).get().get_id();
		
		List<Order> orders = orderRepo.findByUserId(userId);
		List<OrderResponse> orderResponses = new ArrayList<>();
		
		for(Order order:orders) {
			orderResponses.add(getOrderById(order.get_id()));
		}
		return orderResponses;
	}
	
	
}
