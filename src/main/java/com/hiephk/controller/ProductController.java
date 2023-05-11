package com.hiephk.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.hiephk.payload.response.ProductResponse;
import com.hiephk.payload.response.SearchProductResponse;
import com.hiephk.service.ProductService;
import com.hiephk.service.RatingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping("")
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
	
	@GetMapping("/{productId}")
	public ProductResponse getOneProduct(@PathVariable int productId){
		Product product = productService.findById(productId).get();
		return ProductResponse.builder()
				._id(productId)
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.brand(product.getBrand())
				.category(product.getCategory())
				.image(product.getImage())
				.reviews(ratingService.findByProductId(productId))
				.build();
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
	public SearchProductResponse searchProduct(@RequestParam(name = "page") int page, @RequestParam(name = "brand") String brand,
			@RequestParam(name = "category") String category, @RequestParam(name = "query") String query){
		List<String> brands = new ArrayList<>();
		List<String> categories = new ArrayList<>();
		List<Product> listAll = productService.findAll();
		
		for(Product product:listAll) {
			if(!brands.contains(product.getBrand()))
				brands.add(product.getBrand());
			if(!categories.contains(product.getCategory()))
				categories.add(product.getCategory());
		}
		
		List<Product> listSearch = productService.searchProduct(page, brand, category, query);
		
		return SearchProductResponse.builder()
				.countProducts(listSearch.size())
				.productDocs(listSearch)
				.brands(brands)
				.categories(categories)
				.page(1)
				.pages(1)
				.build();
	}
}
