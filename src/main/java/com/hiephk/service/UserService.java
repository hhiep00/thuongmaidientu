package com.hiephk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.User;
import com.hiephk.payload.request.ChangePasswordRequest;
import com.hiephk.payload.request.RegisterRequest;
import com.hiephk.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public User save(RegisterRequest registerRequest) {
		return userRepo.save(User.builder()
				.username(registerRequest.getUsername())
				.email(registerRequest.getEmail())
				.password(registerRequest.getPassword())
				.build());
	}
	
	public User save(ChangePasswordRequest request, int userId) {
		return userRepo.save(User.builder()
				.id(userId)
				.email(request.getEmail())
				.password(request.getPassword())
				.build());
	}

	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	
	
}
