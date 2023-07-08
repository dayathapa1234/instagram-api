package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.insta.instagram.instagramapi.modal.User> opt = userRepository.findByEmail(username);

        if(opt.isPresent()){
            com.insta.instagram.instagramapi.modal.User user=opt.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            return new User(user.getEmail(), passwordEncoder.encode(user.getPassword()),authorities);
        }

        throw new BadCredentialsException("User not found with email "+username);
    }
}
