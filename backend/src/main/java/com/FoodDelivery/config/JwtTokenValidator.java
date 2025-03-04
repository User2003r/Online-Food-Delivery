package com.FoodDelivery.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//		You get token with Bearer keyword to remove Bearer and one space from token we use substring method
		if(jwt != null)
		{
			jwt = jwt.substring(7);
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				Claims claim = Jwts
						.parser()
						.verifyWith(key) 
						.build()
						.parseSignedClaims(jwt)
						.getPayload();
				String email = String.valueOf(claim.get("email"));
				String authorities = String.valueOf(claim.get("authorities")); // get all authorities as a string
				
				// convert the string of authorities to a list
				List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,auth);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			catch(Exception e)
			{
				throw new BadCredentialsException("Invalid Token.......");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	

	

}
