package com.FoodDelivery.service;

import java.util.List;

import com.FoodDelivery.model.Category;
import com.FoodDelivery.model.Food;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req , Category category , Restaurant restaurant);
	public void deleteFood(long id) throws Exception;
	public List<Food> getRestaurantFood(Long restaurantid, boolean isVeg,boolean isNonVeg,
			boolean isSeansonal, String category);
	public List<Food> searchFood(String keyword);
	public Food findFoodByid(long id) throws Exception;
	public Food updateAvailablityStatus(long id) throws Exception;
	
	
	

}
