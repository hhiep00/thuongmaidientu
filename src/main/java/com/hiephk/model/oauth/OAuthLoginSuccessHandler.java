package com.hiephk.model.oauth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hiephk.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Autowired 
    UserService userService;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        CustomOAuth2User oauth2User = (CustomOAuth2User) authentication.getPrincipal();
//        String oauth2ClientName = oauth2User.getOauth2ClientName();
        String username = oauth2User.getEmail();
        System.out.println("...." + username + "....1");
//        userService.updateAuthenticationType(username, oauth2ClientName);
        System.out.println("AuthenticationSuccessHandler invoked");
		System.out.println("Authentication name: " + authentication.getName());
		CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
		
		userService.processOAuthPostLogin(oauthUser.getEmail());
        response.sendRedirect("http://localhost:8080/products");
    }
 
}