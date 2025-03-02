package com.FoodDelivery.service;

import java.util.List;
import org.springframework.stereotype.Service;

//import org.springframework.stereotype.Service;

import com.FoodDelivery.dto.RestaurantDto;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.request.CreateRestaurantReq;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantReq req , User user);
	public Restaurant updateRestaurant(long id , CreateRestaurantReq updateRestaurant);
	public void deletRestaturant(long id) throws Exception;
	public List<Restaurant> getAllRestaurant();
	public List<Restaurant> searchReataurant(String keyword);
	public Restaurant findRestaurantByid(long id) throws Exception;
	public Restaurant getRestaurantByUserid(long userid) throws Exception;
	public RestaurantDto addFavourites(long id , User user) throws Exception;
	public Restaurant updateRestaurantStatus(long id) throws Exception;
	
}
