package com.FoodDelivery.request;

import java.util.List;

import com.FoodDelivery.model.Address;
import com.FoodDelivery.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantReq {

	private long id;
	private String name;
	private String description;
	private String cusineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
	
	
}
