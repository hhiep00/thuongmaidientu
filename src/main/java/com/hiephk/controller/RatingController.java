package com.hiephk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.Rating;
import com.hiephk.payload.request.RatingRequest;
import com.hiephk.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/{productId}")
	public List<Rating> getAllRating(@PathVariable int productId){
		return ratingService.findByProductId(productId);
	}
	
	@PostMapping("/{productId}")
	public Rating ratingAndComment(@PathVariable int productId, @RequestBody RatingRequest request) {
		return ratingService.save(request, productId);
	}
}
