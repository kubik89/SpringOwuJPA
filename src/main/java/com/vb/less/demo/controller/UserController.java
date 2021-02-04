package com.vb.less.demo.controller;

import com.vb.less.demo.dto.AuthRequest;
import com.vb.less.demo.dto.AuthenticationResponse;
import com.vb.less.demo.entity.User;
import com.vb.less.demo.service.IUserService;
import com.vb.less.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
// він реєструє користувача, створює його
// Цей контролер не має жодного відношення до автентифікації. Нею повністю займається Spring
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // тільки реєстрація юзера, але не автентифікація в систему
    @PostMapping
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse generateJWT(@RequestBody AuthRequest authRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        return new AuthenticationResponse(jwtService.generateToken(authRequest.getUsername(), "bla bla"));
    }
}
