package com.hiephk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Rating;
import com.hiephk.payload.request.RatingRequest;
import com.hiephk.repository.RatingRepo;

@Service
public class RatingService {
	@Autowired
	private RatingRepo ratingRepo;

	public Rating save(RatingRequest request, int productId) {
		return ratingRepo.save(Rating.builder()
				.rating(request.getRating())
				.comment(request.getComment())
				.time(new Date())
				.userId(request.getUserId())
				.productId(productId)
				.build());
	}

	public List<Rating> findByProductId(int productId) {
		return ratingRepo.findByProductId(productId);
	}
	
	
}
