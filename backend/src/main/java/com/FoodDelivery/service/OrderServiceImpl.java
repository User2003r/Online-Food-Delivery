package com.FoodDelivery.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.model.Address;
import com.FoodDelivery.model.Cart;
import com.FoodDelivery.model.CartItem;
import com.FoodDelivery.model.Order;
import com.FoodDelivery.model.OrderItem;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.repository.AddressRepository;
import com.FoodDelivery.repository.OrderItemRepository;
import com.FoodDelivery.repository.OrderRepository;
import com.FoodDelivery.repository.UserRepository;
import com.FoodDelivery.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService; 
	
	@Override
	public Order createOrder(OrderRequest req, User user) throws Exception {
		Address shippingAdress = req.getDeliveryAddress();
		Address savedaddress = addressRepository.save(shippingAdress);
		if(!user.getAddress().contains(savedaddress)) {
			user.getAddress().add(savedaddress);
			userRepository.save(user);
		}
		Restaurant restaurant = restaurantService.findRestaurantByid(req.getRestaurantId());
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreateDate(LocalDate.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDelveryAddress(savedaddress);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId()); 
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItem cartItem : cart.getItems())
		{
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		Long totalPrice = cartService.caculatedCartTotal(cart);
		createdOrder.setItems(orderItems);
		createdOrder.setTotalAmount(totalPrice);
		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrders().add(savedOrder);
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		if(orderStatus.equals("PENDING")
				|| orderStatus.equals("OUT_FOR_DELIVERY")
				|| orderStatus.equals("DELIVERED")
				|| orderStatus.equals("COMPLETED")
		   ){
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Select a valid status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

	@Override
	public List<Order> getAllOrders(Long userId) throws Exception {
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if(orders != null) {
			
				orders.stream()
				.filter(order -> order.getOrderStatus().equals(orderStatus))
				.collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> orderOpt = orderRepository.findById(orderId);
		if(orderOpt.isEmpty())
			throw new Exception("Order not found");
		return orderOpt.get();
	}

}
