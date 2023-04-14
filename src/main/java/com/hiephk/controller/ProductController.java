package com.hiephk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.Product;
import com.hiephk.payload.request.ProductRequest;
import com.hiephk.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
	
	@GetMapping("/{productId}")
	public Product getOneProduct(@PathVariable int productId){
		return productService.findById(productId).get();
	}
	
	@PostMapping("")
	public Product createProduct(@RequestBody ProductRequest productRequest){
		return productService.save(productRequest);
	}
}
