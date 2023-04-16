package com.hiephk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.User;
import com.hiephk.payload.request.ChangePasswordRequest;
import com.hiephk.payload.request.RegisterRequest;
import com.hiephk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User register(@RequestBody RegisterRequest registerRequest) {
		if (userService.findByEmail(registerRequest.getEmail()).isEmpty()
				&& userService.findByUsername(registerRequest.getUsername()).isEmpty()) {
			System.out.println("Success");
			return userService.save(registerRequest);
		}
		System.out.println("username or email is existed");
		return User.builder().build();
	}
	
//	@PostMapping("/change-password/{userId}")
//	public User changePassword(@PathVariable int userId, @RequestBody ChangePasswordRequest request) {
//		return userService.save(request, userId);
//	}
}
