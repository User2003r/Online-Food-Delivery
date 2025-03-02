package com.FoodDelivery.request;

import com.FoodDelivery.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long restaurantId;
	
	private Address deliveryAddress;
}
