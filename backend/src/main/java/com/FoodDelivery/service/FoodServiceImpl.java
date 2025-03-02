package com.FoodDelivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.model.Category;
import com.FoodDelivery.model.Food;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.repository.FoodRepository;
import com.FoodDelivery.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setName(req.getName());
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setIngredients(req.getIngredients());
		food.setPrice(req.getPrice());
		food.setSeasonal(req.isSeasonal());
		food.setVegetarian(req.isVeg());
		restaurant.getFood().add(food);
		foodRepository.save(food);
		return food;
	}

	@Override
	public void deleteFood(long id) throws Exception {
//		Food food = foodRepository.findByFoodId(id);
//		food.setRestaurant(null);
//		foodRepository.deleteById(id);
//		foodRepository.save(food);
	}

	@Override
	public List<Food> getRestaurantFood(Long restaurantid, boolean isVeg, boolean isNonVeg, 
			boolean isSeansonal,String category) {
		List<Food> foods = foodRepository.findRestaurantById(restaurantid);
		if(isVeg) {
			foods = filterByVegeterian(foods, isVeg);
		}
		if(isNonVeg) {
			foods = filterByNonVegeterian(foods, isNonVeg);
		}
		if(isSeansonal) {
			foods = filterBySeansonal(foods,isSeansonal);
		}
		if(category != null && !category.equals("")) {
			foods = filterByCategory(foods, category);
		}
		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String category) {
		return foods
				.stream()
				.filter(food -> {
					if(food.getFoodCategory() != null)
						return food.getFoodCategory().getName().equals(category);
					return false;
				})
				.collect(Collectors.toList());
	}

	private List<Food> filterBySeansonal(List<Food> foods, boolean isSeansonal) {
		return foods
				.stream()
				.filter(food -> food.isSeasonal() == isSeansonal)
				.collect(Collectors.toList());
	}

	private List<Food> filterByNonVegeterian(List<Food> foods, boolean isNonVeg) {
		return foods
				.stream()
				.filter(food -> food.isVegetarian() == false)
				.collect(Collectors.toList());
	}

	private List<Food> filterByVegeterian(List<Food> foods, boolean isVeg) {
		return foods
				.stream() // This converts the List<Food> into a Stream.
				// A Stream allows for functional-style operations on collections of objects.
				.filter(food -> food.isVegetarian() == isVeg) // filter food if both values matches
				.collect(Collectors.toList()); // Convert stream into list 
	}

	@Override
	public List<Food> searchFood(String keyword) {
//		return foodRepository.findFoodByName(keyword);
		List<Food> list = new ArrayList<>();
		return list;
	}

	@Override
	public Food findFoodByid(long id) throws Exception {
		Optional<Food> food = foodRepository.findById(id);
		if(food.isEmpty())
			throw new Exception("Food not found");
		return food.get();
	}

	@Override
	public Food updateAvailablityStatus(long id) throws Exception {
		Food food = foodRepository.findFoodById(id);
		if(food == null)
			throw new Exception("Food not found");
		food.setAvailable(!food.isAvailable());
		foodRepository.save(food);
		return food;
	}

}
