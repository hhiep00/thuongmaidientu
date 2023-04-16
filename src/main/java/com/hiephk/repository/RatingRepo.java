package com.hiephk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiephk.model.Rating;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Integer>{
	List<Rating> findByProductId(int productId);
}
