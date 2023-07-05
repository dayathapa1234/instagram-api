package com.insta.instagram.instagramapi.repository;

import com.insta.instagram.instagramapi.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
