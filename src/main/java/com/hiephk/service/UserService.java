package com.hiephk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiephk.model.Provider;
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

	public void save(User user) {
		userRepo.save(user);
	}

	public void processOAuthPostLogin(String email) {
		// TODO Auto-generated method stub
		Optional<User> existUser = findByEmail(email);
		
		if (existUser.isEmpty()) {
			User newUser = new User();
			newUser.setUsername(email);
			newUser.setProvider(Provider.GOOGLE);		
			
			userRepo.save(newUser);
			
			System.out.println("Created new user: " + email);
		}
	}

	public void updateAuthenticationType(String username, String oauth2ClientName) {
		// TODO Auto-generated method stub
		Optional<User> existUserUsername = findByUsername(username);
		Optional<User> existUserMail = findByEmail(username);
		System.out.println("...." + username + "....2");
		System.out.println(existUserMail.isEmpty());
		System.out.println(existUserUsername.isPresent());
		if (existUserUsername.isEmpty() && existUserMail.isEmpty()) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setEmail(oauth2ClientName);
			newUser.setProvider(Provider.valueOf(oauth2ClientName.toUpperCase()));	
			newUser.setRole("USER");
			System.out.println(newUser.toString());
			userRepo.save(newUser);
			
			System.out.println("Created new user: " + username);
			System.out.println("...." + username + "....3");
		}
	}

	
	
}
