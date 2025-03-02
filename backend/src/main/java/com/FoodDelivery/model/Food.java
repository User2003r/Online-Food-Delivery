package com.FoodDelivery.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String description;
	
	private long price;
	
	@ManyToOne
	private Category foodCategory;
	
	@Column(length = 1000)
	@ElementCollection // Used for creating different table for images
	private List<String> images;
	
	private boolean available;
	
	@ManyToOne 
	private Restaurant restaurant;
	
	private boolean isVegetarian;
	
	private boolean isSeasonal;
	
	@ManyToMany
	private List<Ingredients> ingredients = new ArrayList<>();
	
	private Date creationDate;
}
