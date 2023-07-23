package com.insta.instagram.instagramapi.controller;

import com.insta.instagram.instagramapi.dto.LoginResponseDTO;
import com.insta.instagram.instagramapi.dto.RegistrationDTO;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.UserRepository;
import com.insta.instagram.instagramapi.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepo;


    @PostMapping(value = "/signup")
    public ResponseEntity<User> registerUserHandler(@RequestBody RegistrationDTO registration) throws UserException {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(registration.getPassword());
        user.setEmail(registration.getEmail());
        user.setName(registration.getName());

        User createdUser = authService.registerUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @GetMapping(value = "/signup")
    public ResponseEntity<User> signinUserHandler(@RequestBody RegistrationDTO body) {

        LoginResponseDTO login = authService.userSignin(body.getUsername(), body.getPassword());
        return ResponseEntity.accepted().header(HttpHeaders.AUTHORIZATION, login.getJwt()).body(login.getUser());
    }
}
