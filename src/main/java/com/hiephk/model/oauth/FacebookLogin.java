package com.hiephk.model.oauth;


import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


import com.hiephk.model.User;
import com.hiephk.service.UserService;
import com.hiephk.service.impl.JwtServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@Controller
public class FacebookLogin {

    @Autowired
    private FacebookUtils restFb;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtServiceImpl jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @RequestMapping("/login-facebook")
    public RedirectView loginFacebook(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return new RedirectView("/login");
        }

        String accessToken = restFb.getToken(code);

        User FBuser = restFb.getUserInfo(accessToken);

        Optional<User> userExist = userService.findByEmail(FBuser.getEmail());
        if(userExist.isEmpty()){
            userService.save(FBuser);
        }

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(FBuser.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        Cookie cookie = new Cookie("token", token);
        System.out.println(token);
        response.addCookie(cookie);

        return new RedirectView("http://localhost:3000/");
    }

}
