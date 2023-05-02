package com.hiephk.service;

import com.hiephk.payload.request.AuthenticationRequest;
import com.hiephk.payload.request.RegisterRequest;
import com.hiephk.payload.response.AuthenticationResponse;

public interface AuthenticationService {

	AuthenticationResponse authenticate(AuthenticationRequest request);

	AuthenticationResponse register(RegisterRequest request);

}
