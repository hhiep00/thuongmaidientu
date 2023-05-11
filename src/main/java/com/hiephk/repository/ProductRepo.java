package com.hiephk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hiephk.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("select e from Product e "
	          + "where (:brand='' or e.brand=:brand) "
	          + "and (:category='' or e.category=:category) "
	          + "and e.name LIKE %:name%")
    List<Product> advancedSearch(@Param("brand") String brand,
	                               @Param("category") String category,
	                               @Param("name") String name);
}
