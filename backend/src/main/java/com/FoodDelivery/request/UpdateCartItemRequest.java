package com.FoodDelivery.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {

	private Long id;
	
	private int quantity;
}
