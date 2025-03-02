package com.FoodDelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.model.Ingredients;
import com.FoodDelivery.model.IngredientsCategory;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.repository.IngredientCategoryRepository;
import com.FoodDelivery.repository.IngredientItemRepisitory;

@Service
public class IngredientsServiceImpl implements IngredientsService{

	@Autowired
	private IngredientItemRepisitory ingredientsRepository;
	
	@Autowired
	private IngredientCategoryRepository ingredientsCategoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientsCategory createIngredients(String name, Long restaurantId) throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantByid(restaurantId);
		IngredientsCategory  category = new IngredientsCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		ingredientsCategoryRepository.save(category);
		return category;
	}

	@Override
	public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientsCategory> opt = ingredientsCategoryRepository.findById(id);
		if(opt.isEmpty())
			throw new Exception("Ingredients Category not found");
		return opt.get();
	}

	@Override
	public List<IngredientsCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		restaurantService.findRestaurantByid(id);
		return ingredientsCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public List<Ingredients> findRestaurantIngredients(Long restaurantId) {
		return ingredientsRepository.findRestaurantByid(restaurantId);
	}

	@Override
	public Ingredients createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantByid(restaurantId);
		IngredientsCategory category = findIngredientCategoryById(categoryId);
		Ingredients item = new Ingredients();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		Ingredients ingredient = ingredientsRepository.save(item);
		category.getIngredents().add(ingredient);
		return ingredient;
	}

	@Override
	public Ingredients updateStock(Long id) throws Exception {
		Optional<Ingredients> opt = ingredientsRepository.findById(id);
		if(opt.isEmpty())
			throw new Exception("Ingredients not found");
		Ingredients ingredient = opt.get();
		ingredient.setStock(!ingredient.isStock());
		return ingredientsRepository.save(ingredient);
	}
	
	
}
