package com.hiephk.payload.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressRequest {
	private String address;
	private String city;
	private String country;
	private String postalCode;
}
