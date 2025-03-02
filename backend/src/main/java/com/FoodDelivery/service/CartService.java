package com.FoodDelivery.service;

import com.FoodDelivery.model.Cart;
import com.FoodDelivery.model.CartItem;
import com.FoodDelivery.request.AddCartItemRequest;

public interface CartService {

	public CartItem addItemToCart(AddCartItemRequest req , String jwt) throws Exception;
	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;
	public Cart removedItemFromCart(Long cartItemId, String jwt) throws Exception;
	public Long caculatedCartTotal(Cart cart) throws Exception;
	public Cart findCartById(Long id) throws Exception;
	public Cart findCartByUserId(Long id) throws Exception;
	public Cart clearCart(Long id) throws Exception;
	
}
