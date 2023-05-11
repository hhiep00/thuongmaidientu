package com.hiephk.payload.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
	private int totalPrice;
	private List<ProductOrderRequest> cartItems;
	private AddressRequest shippingAddress;
}
