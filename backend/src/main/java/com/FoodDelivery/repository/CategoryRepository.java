package com.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
	
	public List<Category> findByRestaurantId(Long id);

}
