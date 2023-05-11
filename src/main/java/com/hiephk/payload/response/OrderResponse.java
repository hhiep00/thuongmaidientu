package com.hiephk.payload.response;

import java.util.Date;
import java.util.List;

import com.hiephk.model.Address;
import com.hiephk.model.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
	private int user;
	private int _id;
	private int __v;
	private float totalPrice;
	private boolean isIsPaid;
	private Address shippingAddress;
	private Date createdAt;
	private Date updatedAt;
	private List<ProductDetailResponse> cartItems;
}
