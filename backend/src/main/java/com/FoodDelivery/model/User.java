package com.FoodDelivery.model;

import java.util.ArrayList;
import java.util.List;

import com.FoodDelivery.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY) // this password field only works when you add password 
	//and not get this field when access user information
	private String password;
	
	private USER_ROLE role=USER_ROLE.ROLE_CUSTOMER;
	
	@JsonIgnore // This annotation is used so that when we call user entity we don't get order list
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer") // users have one to many relationship with orders
	private List<Order> orders = new ArrayList<>(); 
	
	@ElementCollection
	private List<RestaurantDto> favourites = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL) // single user have multiple addresses | we give cascadeType.all 
	// because when we delete paticular user then all addresses related to that user is also deleted
	private List<Address> address = new ArrayList<>();
}
