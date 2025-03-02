package com.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.model.Order;
import com.FoodDelivery.model.User;
import com.FoodDelivery.service.OrderService;
import com.FoodDelivery.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity<List<Order>> getOrderHistory(
			@PathVariable Long id,
			@RequestParam(required = false) String order_Status,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		List<Order> orders = orderService.getRestaurantOrder(id,order_Status);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	
	@PutMapping("/order/{orderId}/{orderStatus}")
	public ResponseEntity<Order> updateOrderStatus(
			@PathVariable Long orderid,
			@PathVariable String orderStatus,
			@RequestHeader("Authorization")String jwt) throws Exception
	{
		User user = userService.findUserByJwtToken(jwt);
		Order order = orderService.updateOrder(orderid, orderStatus);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	
}
