package com.insta.instagram.instagramapi;

import com.insta.instagram.instagramapi.modal.Role;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.RoleRepository;
import com.insta.instagram.instagramapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class InstagramApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {

			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role admin = roleRepository.save(new Role(1,"ADMIN"));
			roleRepository.save(new Role(2,"USER"));

			Set<Role> adminRoles = new HashSet<Role>();
			adminRoles.add(admin);

			User userAdmin = new User();
			userAdmin.setUsername("Admin");
			userAdmin.setEmail("Admin@gmail.com");
			userAdmin.setPassword(passwordEncoder.encode("password"));
			userAdmin.setAuthorities(adminRoles);

			userRepository.save(userAdmin);
		};
	}

}
