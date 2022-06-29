package com.example.handsOnProject2.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.handsOnProject2.model.AuthenticationRequest;
import com.example.handsOnProject2.model.JWTResponse;
import com.example.handsOnProject2.security.CustomUserDetailsService;
import com.example.handsOnProject2.security.JWTUtil;

@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Username or password incorrect");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtutil.generateToken(userDetails);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		String role = null;
		for(GrantedAuthority g: authorities) {
			if(g.getAuthority().equals("ROLE_ADMIN")) {
				role = "true";
			}
			else if(g.getAuthority().equals("ROLE_STUDENT")) {
				role = "false";
			}
		}
		
		return ResponseEntity.ok(new JWTResponse(jwt,role));
	}
}
