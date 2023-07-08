package com.insta.instagram.instagramapi.controller;

import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.UserRepository;
import com.insta.instagram.instagramapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping(value = "/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @GetMapping(value ="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> signinUserHandler(Authentication auth) throws BadCredentialsException {

        Optional<User> opt = userRepo.findByEmail(auth.getName());
        if (opt.isPresent()){
            return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
        }

        throw new BadCredentialsException("Invalid username or password");
    }
}
