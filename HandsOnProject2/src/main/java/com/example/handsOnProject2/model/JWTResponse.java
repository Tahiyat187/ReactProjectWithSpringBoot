package com.example.handsOnProject2.model;

import lombok.Getter;

@Getter
public class JWTResponse {

	private final String jwtToken;
	private final String role;
	
	public JWTResponse(String jwtToken,String role) {
		this.jwtToken = jwtToken;
		this.role = role;
	}
}
