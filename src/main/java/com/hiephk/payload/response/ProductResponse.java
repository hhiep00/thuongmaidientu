package com.hiephk.payload.response;

import java.util.Date;
import java.util.List;

import com.hiephk.model.Rating;
import com.hiephk.payload.request.ProductRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
	private int _id;
	private String name;
	private String description;
	private int price;
	private String brand;
	private String category;
	private String image;
	private List<Rating> reviews;
	private Date createdAt;
}
