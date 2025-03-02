package com.FoodDelivery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.model.Cart;
import com.FoodDelivery.model.CartItem;
import com.FoodDelivery.model.Food;
import com.FoodDelivery.model.User;
import com.FoodDelivery.repository.CartItemRepository;
import com.FoodDelivery.repository.CartRepository;
import com.FoodDelivery.request.AddCartItemRequest;

@Service
public class CartServiceImpl  implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private FoodService foodService;

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		
		Food food = foodService.findFoodByid(req.getFoodId());
		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		for(CartItem cartItem : cart.getItems())
		{
			if(cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity()+req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(), newQuantity);
			}
		}
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());
		CartItem savedCartItem = cartItemRepository.save(newCartItem);
		cart.getItems().add(savedCartItem);
		return savedCartItem;
	}

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if(cartItem.isEmpty())
			throw new Exception("cart item not found");
		CartItem item = cartItem.get();
		item.setQuantity(quantity);
		item.setTotalPrice(item.getFood().getPrice() * quantity);
		return cartItemRepository.save(item);
	}

	@Override
	public Cart removedItemFromCart(Long cartItemId, String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);

		Cart cart = cartRepository.findByCustomerId(user.getId());
		
		Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
		if(cartItem.isEmpty())
			throw new Exception("cart item not found");
		CartItem item = cartItem.get();
		cart.getItems().remove(item);
		
		return cartRepository.save(cart);
	}

	@Override
	public Long caculatedCartTotal(Cart cart) throws Exception {

		Long total = 0L;
		for(CartItem item : cart.getItems())
		{
			total += item.getFood().getPrice() * item.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optCart = cartRepository.findById(id);
		if(optCart.isEmpty())
			throw new Exception("cart not found");
		return optCart.get();
	}

	@Override
	public Cart findCartByUserId(Long id) throws Exception {
		Cart cart = cartRepository.findByCustomerId(id);
		cart.setTotal(caculatedCartTotal(cart));
		return cart;
		
		
	}

	@Override
	public Cart clearCart(Long id) throws Exception {
		Cart cart = cartRepository.findByCustomerId(id);
		cart.getItems().clear();
		return cartRepository.save(cart);
	}

	
}
