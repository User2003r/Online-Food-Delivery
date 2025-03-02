package com.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.dto.RestaurantDto;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.service.RestaurantService;
import com.FoodDelivery.service.UserService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurantList = restaurantService.searchReataurant(keyword);
		return new ResponseEntity<>(restaurantList,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Restaurant>> getAllRestaurant(
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurantList = restaurantService.getAllRestaurant();
		return new ResponseEntity<>(restaurantList,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantByid(
			@PathVariable long id,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantByid(id);
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/add-favourite")
	public ResponseEntity<RestaurantDto> addFavourite(
			@PathVariable long id,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		RestaurantDto restaurant = restaurantService.addFavourites(id, user);
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}
	
	
}


















