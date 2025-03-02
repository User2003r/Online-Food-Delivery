package com.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.Ingredients;

@Repository
public interface IngredientItemRepisitory extends JpaRepository<Ingredients, Long>{

	List<Ingredients> findRestaurantByid(Long id);
}
