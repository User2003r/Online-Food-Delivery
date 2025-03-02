package com.FoodDelivery.request;

import java.util.List;

import com.FoodDelivery.model.Category;
import com.FoodDelivery.model.Ingredients;

import lombok.Data;

@Data
public class CreateFoodRequest {

	private String name;
	
	private String description;
	
	private Long price;
	
	private Category category;
	
	private List<String> images;
	
	private Long RestaurantId;
	
	private boolean veg;
	
	private boolean seasonal;
	
	private List<Ingredients> ingredients;
}
