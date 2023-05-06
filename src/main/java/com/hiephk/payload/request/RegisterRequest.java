package com.hiephk.payload.request;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
	private String email;
	private String username;
	private String password;
	private String repeatPassword;
	private String fullname;
	private String phoneNumber;
}
