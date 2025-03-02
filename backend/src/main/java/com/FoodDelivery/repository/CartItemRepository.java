package com.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	

}
