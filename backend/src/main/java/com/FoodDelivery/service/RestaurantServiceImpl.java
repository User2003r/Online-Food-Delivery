package com.FoodDelivery.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.dto.RestaurantDto;
import com.FoodDelivery.model.Address;
import com.FoodDelivery.model.Restaurant;
import com.FoodDelivery.model.User;
import com.FoodDelivery.repository.AddressRepository;
import com.FoodDelivery.repository.RestaurantRepository;
import com.FoodDelivery.repository.UserRepository;
import com.FoodDelivery.request.CreateRestaurantReq;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRep;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRep;
	
	


	public Restaurant createRestaurant(CreateRestaurantReq req, User user) {
		Restaurant addRestaurant = new Restaurant();
		addRestaurant.setName(req.getName());
		addRestaurant.setAddress(req.getAddress());
		addRestaurant.setContactInformation(req.getContactInformation());
		addRestaurant.setCusineType(req.getCusineType());
		addRestaurant.setDescription(req.getDescription());
		addRestaurant.setId(req.getId());
		addRestaurant.setImage(req.getImages());
		addRestaurant.setOpeningHours(req.getOpeningHours());
		addRestaurant.setRegistration(LocalDateTime.now());
		addRestaurant.setOwner(user);

		addressRepository.save(req.getAddress());
		addRestaurant =  restaurantRep.save(addRestaurant);
		return addRestaurant;
	}

	public Restaurant updateRestaurant(long id, CreateRestaurantReq updateRestaurant) {
		Restaurant restaurant = restaurantRep.findRestaurantById(id);
		if(restaurant == null) {
			
			throw new RuntimeException("Restaurant Not Found");
		}
		restaurant.setName(updateRestaurant.getName());
		restaurant.setAddress(updateRestaurant.getAddress());
		restaurant.setContactInformation(updateRestaurant.getContactInformation());
		restaurant.setCusineType(updateRestaurant.getCusineType());
		restaurant.setDescription(updateRestaurant.getDescription());
		restaurant.setImage(updateRestaurant.getImages());
		restaurant.setOpeningHours(updateRestaurant.getOpeningHours());
		addressRepository.save(updateRestaurant.getAddress());
		restaurant =  restaurantRep.save(restaurant);
		return restaurant;
	}

	public void deletRestaturant(long id) throws Exception {
		Restaurant restaurant = findRestaurantByid(id);
		restaurantRep.delete(restaurant);
		
		
		
	}

	public List<Restaurant> getAllRestaurant() {
		return restaurantRep.findAll();
	}

	public List<Restaurant> searchReataurant(String keyword) {
			List<Restaurant> list = restaurantRep.findRestaurantByName(keyword);
			return list;
	}

	public Restaurant findRestaurantByid(long id) throws Exception {
		Optional<Restaurant> opt = restaurantRep.findById(id);
		if(opt.isEmpty()) {
			
			throw new Exception("Restaurant not found by given id");
		}
		return opt.get();
	}

	public Restaurant getRestaurantByUserid(long userid) throws Exception {
		Restaurant restaurant = restaurantRep.findRestaurantByOwnerId(userid);
		if(restaurant == null) {
			
			throw new Exception("Restaurant not found with userid");
		}
		return restaurant;
	}

	public RestaurantDto addFavourites(long id, User user) throws Exception {
		Restaurant restaurant = restaurantRep.findRestaurantById(id);
		if(restaurant == null) {
			
			throw new Exception("Restaurant not found");
		}
		RestaurantDto dto = new RestaurantDto();
		dto.setTitle(restaurant.getName());
		dto.setId(id);
		dto.setImages(restaurant.getImage());
		dto.setDescription(restaurant.getDescription());
		List<RestaurantDto> favouriteList = user.getFavourites();
		boolean isPresent = false;
		for(RestaurantDto favourite : favouriteList)
		{
			if(favourite.getId() == dto.getId())
			{
				isPresent = true;
				break;
			}
		}
		if(isPresent) 
			favouriteList.removeIf(favourite -> favourite.getId() == dto.getId());
		else
			favouriteList.add(dto);
		System.out.println(favouriteList);
		userRep.save(user);
		return dto;
	}

	public Restaurant updateRestaurantStatus(long id) throws Exception {
		Restaurant restaurant = restaurantRep.findRestaurantById(id);
		if(restaurant == null) {
			throw new Exception("Restaurant not found");
		}
		restaurant.setOpen(!restaurant.isOpen());
			
		return restaurantRep.save(restaurant);
	}

	
}
