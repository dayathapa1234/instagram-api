package com.insta.instagram.instagramapi.security;

import com.insta.instagram.instagramapi.dto.LoginResponseDTO;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Role;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.RoleRepository;
import com.insta.instagram.instagramapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In UserDetails service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username+" is not found"));
    }

    public User registerUser(User user) throws UserException {

        Optional<User> doesEmailExistInDB = userRepository.findByEmail(user.getEmail());
        Optional<User> doesUsernameExistInDB = userRepository.findByUsername(user.getUsername());
        Optional<Role>  userRole = roleRepository.findByAuthority("USER");

        if(doesEmailExistInDB.isPresent()){
            throw new UserException("Email already exist");
        }

        if (doesUsernameExistInDB.isPresent()) {
            throw new UserException("Username is already taken");
        }

        if(user.getEmail()==null || user.getUsername()==null || user.getPassword()==null || user.getName()==null){
            throw new UserException("All fields are required");
        }
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());
        newUser.setAuthorities(roles);

        return userRepository.save(newUser);
    }

    public LoginResponseDTO userSignin(String username, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
            String token = tokenService.generateJwt(auth);
            System.out.println(auth.getName());
            return new LoginResponseDTO(userRepository.findByUsername(username).get(),token);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}

