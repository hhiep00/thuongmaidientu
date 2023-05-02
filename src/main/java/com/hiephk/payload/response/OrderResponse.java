package com.hiephk.payload.response;

import java.util.List;

import com.hiephk.model.OrderDetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	private int userId;
	private int orderId;
	private String orderStatus;
	private List<OrderDetail> orderDetails;
}
