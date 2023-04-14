package com.hiephk.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Product;
import com.hiephk.payload.request.ProductRequest;
import com.hiephk.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;

	public Product save(ProductRequest productRequest) {
		
		return productRepo.save(Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.sale(0.0f)
				.publish_on(new Date())
				.views(0)
				.price(productRequest.getPrice())
				.build());
	}

	public List<Product> findAll() {
		return productRepo.findAll();
	}

	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}
	
	
}
