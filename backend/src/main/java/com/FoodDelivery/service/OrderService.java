package com.FoodDelivery.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.FoodDelivery.model.Order;
import com.FoodDelivery.model.User;
import com.FoodDelivery.request.OrderRequest;

@Component
public interface OrderService {

	public Order createOrder(OrderRequest req,User user) throws Exception;
	public Order updateOrder(Long orderId,String orderStatus) throws Exception;
	public void cancelOrder(Long orderId) throws Exception;
	public List<Order> getAllOrders(Long userId) throws Exception;
	public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus) throws Exception;
	public Order findOrderById(Long orderId) throws Exception;
	
}
