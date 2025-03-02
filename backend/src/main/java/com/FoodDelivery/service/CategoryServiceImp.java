package com.FoodDelivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.model.Category;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Category createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserid(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserid(id);
		return categoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> category =  categoryRepository.findById(id);
		if(category.isEmpty())
			throw new Exception("Category not found");
		return category.get();
	}

	
}
