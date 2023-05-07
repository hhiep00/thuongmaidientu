package com.hiephk.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

	private String token;
	private String name;
	private String email;
	private boolean isAdmin;
	private int _id;

	public AuthenticationResponse(String token) {
		this.token = token;
	}

}