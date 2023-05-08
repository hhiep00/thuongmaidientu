package com.hiephk.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hiephk.model.Rating;
import com.hiephk.model.User;
import com.hiephk.payload.request.RatingRequest;
import com.hiephk.repository.RatingRepo;

@Service
public class RatingService {
	@Autowired
	private RatingRepo ratingRepo;
	@Autowired
	private UserService userService;

	public Rating save(RatingRequest request, int productId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
		String username = principal.toString();
		
		User user = userService.findByEmail(username).get();
		return ratingRepo.save(Rating.builder()
				.username(username)
				.rating(request.getRating())
				.comment(request.getComment())
				.createdAt(new Date())
				.userId(user.getId())
				.productId(productId)
				.build());
	}

	public List<Rating> findByProductId(int productId) {
		return ratingRepo.findByProductId(productId);
	}

}
