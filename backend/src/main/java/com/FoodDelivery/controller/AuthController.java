package com.FoodDelivery.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FoodDelivery.config.JwtProvider;
import com.FoodDelivery.model.Cart;
import com.FoodDelivery.model.USER_ROLE;
import com.FoodDelivery.model.User;
import com.FoodDelivery.repository.CartRepository;
import com.FoodDelivery.repository.UserRepository;
import com.FoodDelivery.request.LoginRequest;
import com.FoodDelivery.response.AuthResponse;
import com.FoodDelivery.service.CustomUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private JwtProvider jwtProvide;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHanlder(@RequestBody User user) throws Exception
	{
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		if(isEmailExist != null)
			throw new Exception("Email is already used");
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setRole(user.getRole());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvide.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");
		authResponse.setRole(savedUser.getRole());
		return new ResponseEntity<>(authResponse , HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest req) throws Exception
	{
		String userName = req.getEmail();
		String password = req.getPassword();
		
		Authentication authenticate = authenticate(userName , password);
		String jwt = jwtProvide.generateToken(authenticate);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login Successfully");
		Collection<? extends GrantedAuthority> authorities = authenticate.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
		authResponse.setRole(USER_ROLE.valueOf(role));
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}

	private Authentication authenticate(String userName, String password) throws Exception {
		UserDetails user = customUserDetailService.loadUserByUsername(userName);
		System.out.println(user);
		if(user == null)
			throw new Exception("Invalid Username");
		if(!(passwordEncoder.matches(password, user.getPassword())))
			throw new Exception("Invalid Password");
		return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
	}
}
























