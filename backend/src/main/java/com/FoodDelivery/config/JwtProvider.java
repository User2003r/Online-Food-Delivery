package com.FoodDelivery.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication auth)
	{
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String roles = populateAuthorities(authorities);
		
		String jwt = Jwts
				.builder()
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime() * 86400))
				.claim("email",auth.getName())
				.claim("authorities",roles)
				.signWith(key)
				.compact();
				
			return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt)
	{
		jwt = jwt.substring(7);
		Claims claim = Jwts
				.parser()
				.verifyWith(key) 
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
		String email = String.valueOf(claim.get("email"));
		return email;
	}
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities)
	{
		Set<String> auths = new HashSet<>();
		
		for (GrantedAuthority i : authorities) {
			auths.add(i.getAuthority());
		}
		return String.join(",",auths);
		
	}
	
}
