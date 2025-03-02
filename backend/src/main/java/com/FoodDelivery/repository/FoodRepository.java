package com.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.FoodDelivery.model.Food;
//import com.FoodDelivery.model.Restaurant;


public interface FoodRepository extends JpaRepository<Food, Long>{
	
	Food findFoodById(long id);
	
	List<Food> findRestaurantById(long restaurantId);
	
	@Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))") 
	List<Food> findFoodByName(String keyword);

}
