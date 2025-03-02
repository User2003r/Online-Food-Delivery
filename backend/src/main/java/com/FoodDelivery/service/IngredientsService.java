package com.FoodDelivery.service;

import java.util.List;

import com.FoodDelivery.model.Ingredients;
import com.FoodDelivery.model.IngredientsCategory;

public interface IngredientsService {

	public IngredientsCategory createIngredients(String name,Long restaurantId) throws Exception;
	
	public IngredientsCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
	
	public List<Ingredients> findRestaurantIngredients(Long restaurantId);

	public Ingredients createIngredientsItem(Long restaurantId,String ingredientName,Long categoryId) 
			throws Exception;
	
	public Ingredients updateStock(Long id) throws Exception;

}
