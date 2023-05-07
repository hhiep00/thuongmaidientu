package com.hiephk.model.oauth;

import java.io.IOException;
import java.util.Optional;

import com.hiephk.model.User;
import com.hiephk.service.JwtService;
import com.hiephk.service.UserService;
import com.hiephk.service.impl.JwtServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GoogleLogin {

    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtServiceImpl jwtTokenUtil;
    @Autowired
	private JwtService jwtService;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @GetMapping("/login-google")
    public RedirectView loginGoogle(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {

        String code = request.getParameter("code");
        System.out.println(code);
        if (code == null || code.isEmpty()) {
            return new RedirectView("/");
        }

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        
        Optional<User> userExist = userService.findByEmail(googlePojo.getEmail());
        System.out.println(userExist);
        if(userExist.isEmpty()){
            User user = new User();
            user.setEmail(googlePojo.getEmail());
            user.setUsername(googlePojo.getEmail());
            user.setPassword("password");
            user.setRole("USER");
            userService.save(user);
        }

//        final UserDetails userDetails = jwtInMemoryUserDetailsService
//                .loadUserByUsername(googlePojo.getEmail());
//        System.out.println(userDetails);
        User person = userService.findByEmail(googlePojo.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(person);
//        final String token = jwtTokenUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("token", jwtToken);
        System.out.println(jwtToken);
        response.addCookie(cookie);

        RedirectView redirectView = new RedirectView("http://localhost:3000/");

        // Add the token as a query parameter in the URL
        redirectView.addStaticAttribute("token", jwtToken);
        return redirectView;
    }
}
