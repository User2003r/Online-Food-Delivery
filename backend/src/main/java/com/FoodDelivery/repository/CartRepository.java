package com.FoodDelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FoodDelivery.model.Cart;

@Repository
public interface CartRepository  extends JpaRepository<Cart , Long>{
	
	public Cart findByCustomerId(Long userid);

}
