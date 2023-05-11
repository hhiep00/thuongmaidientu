package com.hiephk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiephk.model.User;
import com.hiephk.payload.request.ChangePasswordRequest;
import com.hiephk.payload.request.RegisterRequest;
import com.hiephk.payload.response.UserResponse;
import com.hiephk.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@PostMapping("/register")
//	public User register(@RequestBody RegisterRequest registerRequest) {
//		if (userService.findByEmail(registerRequest.getEmail()).isEmpty()
//				&& userService.findByUsername(registerRequest.getUsername()).isEmpty()) {
//			System.out.println("Success");
//			return userService.save(registerRequest);
//		}
//		System.out.println("username or email is existed");
//		return User.builder().build();
//	}
	
//	@PostMapping("/change-password/{userId}")
//	public User changePassword(@PathVariable int userId, @RequestBody ChangePasswordRequest request) {
//		return userService.save(request, userId);
//	}
	
	@GetMapping("")
	public List<UserResponse> findAll() {
		List<User> list = userService.findAll();
		List<UserResponse> listUserResponses = new ArrayList<>();
		for(User user:list) {
			boolean k = false;
			if(user.getRole() != null)
				if(!user.getRole().equals("ADMIN"))
					k=true;
			listUserResponses.add(UserResponse.builder()
				._id(user.get_id())
				.name(user.getFullName())
				.username(user.getUsername())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.isIsAdmin(k)
				.createdAt(new Date())
				.build());
		}
		return listUserResponses;
	}
	
	@GetMapping("/{userId}")
	public UserResponse findOne(@PathVariable int userId) {
		User user = userService.findById(userId).get();
		boolean k = false;
		if(user.getRole() != null)
			if(!user.getRole().equals("ADMIN"))
				k=true;
		
		return UserResponse.builder()
				._id(userId)
				.name(user.getFullName())
				.username(user.getUsername())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.role(user.getRole())
				.isIsAdmin(k)
				.createdAt(new Date())
				.build();
	}
}
