package com.FoodDelivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodDelivery.config.JwtProvider;
import com.FoodDelivery.model.User;
import com.FoodDelivery.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public User findUserByJwtToken(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRep.findByEmail(email);
		return user;
	}

	public User findUserByEmail(String email) throws Exception {
		User user = userRep.findByEmail(email);
		if(user == null)
		{
			throw new Exception("User not found");
			
		}
		return user;
	}

}
