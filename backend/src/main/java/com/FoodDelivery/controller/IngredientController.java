package com.FoodDelivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.model.Ingredients;
import com.FoodDelivery.model.IngredientsCategory;
import com.FoodDelivery.request.IngredientRequest;
import com.FoodDelivery.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

	@Autowired
	private IngredientsService ingredientService;
	
	
	
	@PostMapping("")
	public ResponseEntity<Ingredients> createIngredientCategory(
			@RequestBody IngredientRequest req
			) throws Exception
	{
		Ingredients item = ingredientService.createIngredientsItem(req.getRestaurantId(), 
				req.getName(),req.getRestaurantId());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/stock")
	public ResponseEntity<Ingredients> updateStock(
			@PathVariable Long id
			) throws Exception
	{
		Ingredients item = ingredientService.updateStock(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<Ingredients>> getRestaurantIngredients(
			@PathVariable Long id
			) throws Exception
	{
		List<Ingredients> ingredientList = ingredientService.findRestaurantIngredients(id);
		return new ResponseEntity<>(ingredientList,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientsCategory>> getRestaurantCategory(
			@PathVariable Long id
			) throws Exception
	{
		List<IngredientsCategory> ingredientList = ingredientService.findIngredientsCategoryByRestaurantId(id);
		return new ResponseEntity<>(ingredientList,HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
