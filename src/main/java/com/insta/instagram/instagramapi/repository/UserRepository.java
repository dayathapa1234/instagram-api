package com.insta.instagram.instagramapi.repository;

import com.insta.instagram.instagramapi.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE u.id IN :users",nativeQuery = true)
    public List<User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);

    @Query(value = "SELECT DISTINCT * FROM users u WHERE u.username LIKE %:query% OR u.email LIKE %:query%", nativeQuery = true)
    public List<User> findByQuery(@Param("query") String query);
}
