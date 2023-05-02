package com.hiephk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.Product;
import com.hiephk.payload.request.ProductRequest;
import com.hiephk.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
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
	
	@PutMapping("{productId}")
	public Product createProduct(@PathVariable int productId, @RequestBody ProductRequest productRequest){
		Product product = productService.findById(productId).get();
		product.setBrand(productRequest.getBrand());
		product.setCategory(productRequest.getCategory());
		product.setDescription(productRequest.getDescription());
		product.setImage(productRequest.getImage());
		product.setName(product.getName());
		product.setPrice(productRequest.getPrice());
		
		return productService.save(product);
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam(name = "page") int page, @RequestParam(name = "brand") String brand,
			@RequestParam(name = "category") String category, @RequestParam(name = "query") String query){
		return productService.searchProduct(page, brand, category, query);
	}
}
