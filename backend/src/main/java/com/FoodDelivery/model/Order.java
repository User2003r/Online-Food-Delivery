package com.FoodDelivery.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore
	@ManyToOne // Multiple orders from one restaurant
	private Restaurant restaurant; 
	
	private long totalAmount;
	
	private String OrderStatus;
	
	private LocalDate createDate;
	
	@ManyToOne // Because multiple orders from same address
	private Address delveryAddress;
	
	@OneToMany
	private List<OrderItem> items;
}
