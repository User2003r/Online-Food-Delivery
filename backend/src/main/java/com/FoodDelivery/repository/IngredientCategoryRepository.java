package com.FoodDelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.IngredientsCategory;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory, Long>{

	List<IngredientsCategory> findByRestaurantId(Long id);
}
