package com.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	
	
	public Restaurant findRestaurantById(long id);
	
	public Restaurant findRestaurantByOwnerId(long id);
	
	@Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%',:keyword,'%'))"
			+ "OR lower(r.cusineType) LIKE lower(concat('%',:keyword,'%'))")
	public List<Restaurant> findRestaurantByName(String keyword);
	
}
