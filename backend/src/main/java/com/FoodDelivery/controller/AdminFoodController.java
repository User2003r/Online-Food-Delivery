package com.FoodDelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.model.Food;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.request.CreateFoodRequest;
import com.FoodDelivery.response.MessageResponse;
import com.FoodDelivery.service.FoodService;
import com.FoodDelivery.service.RestaurantService;
import com.FoodDelivery.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("")
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantByid(req.getRestaurantId());
		Food food =  foodService.createFood(req,req.getCategory(),restaurant);
		return new ResponseEntity<>(food,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		foodService.deleteFood(id);
		MessageResponse message = new MessageResponse();
		message.setMessage("Food Deleted Successfully");
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/updateAvailability")
	public ResponseEntity<Food> updateAvailability(@PathVariable long id, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.updateAvailablityStatus(id);
		return new ResponseEntity<>(food,HttpStatus.OK);
	}
	
	
	
	
	
	
}









