package com.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.request.CreateRestaurantReq;
import com.FoodDelivery.response.MessageResponse;
import com.FoodDelivery.service.RestaurantService;
import com.FoodDelivery.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantReq req,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.createRestaurant(req, user);
		return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant( @RequestBody CreateRestaurantReq req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable long id) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurant(id, req);
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaurant(@PathVariable long id,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		restaurantService.deletRestaturant(id); 
		MessageResponse res = new MessageResponse();	
		res.setMessage("Restaurant deleted successfully");
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updateStatus(@PathVariable long id,
			@RequestBody CreateRestaurantReq req,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByOwnerid(
			@RequestBody CreateRestaurantReq req,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.getRestaurantByUserid(user.getId());
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	
	
	
}
















