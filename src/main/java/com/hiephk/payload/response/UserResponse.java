package com.hiephk.payload.response;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	private int _id;
	private String email;
	private String name;
	private String phoneNumber;
	private String username;
	private String role;
	private String password;
	private Date createdAt;
	private String updatedAt;
	private boolean isIsAdmin;
}
