package com.hiephk.service.impl;


import lombok.RequiredArgsConstructor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiephk.model.Role;
import com.hiephk.model.User;
import com.hiephk.payload.request.AuthenticationRequest;
import com.hiephk.payload.request.RegisterRequest;
import com.hiephk.payload.response.AuthenticationResponse;
import com.hiephk.service.AuthenticationService;
import com.hiephk.service.JwtService;
import com.hiephk.service.UserService;

import jakarta.servlet.http.Cookie;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		User user = User.builder()
				.username(request.getEmail())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.phoneNumber(request.getPhoneNumber())
				.fullName(request.getFullname())
				.role("USER")
				.build();
		userService.save(user);
		System.out.println(user.get_id());
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				._id(user.get_id())
				.name(user.getEmail())
				.email(user.getEmail())
				.isAdmin(true)
				.token(jwtToken)
				.build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		User person = userService.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(person);
		return AuthenticationResponse.builder()
				._id(person.get_id())
				.name(person.getEmail())
				.email(person.getEmail())
				.isAdmin(true)
				.token(jwtToken)
				.build();
	}

}