package com.insta.instagram.instagramapi.repository;

import com.insta.instagram.instagramapi.modal.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByAuthority(String authority);
}
