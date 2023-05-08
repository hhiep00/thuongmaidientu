package com.hiephk.payload.response;

import java.util.Date;
import java.util.List;

import com.hiephk.model.Product;
import com.hiephk.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProductResponse {
	private int countProducts;
	private List<Product> productDocs;
	private List<String> brands;
	private List<String> categories;
	private int page = 1;
	private int pages = 1;
}
