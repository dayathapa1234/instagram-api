package com.insta.instagram.instagramapi.repository;

import com.insta.instagram.instagramapi.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{

    @Query(value="SELECT * FROM posts p WHERE p.user_id=?1",nativeQuery = true)
    public List<Post> findByUserId(Integer userId);

    @Query(value="SELECT * FROM posts p WHERE p.user_id IN :users ORDER BY p.created_at DESC",nativeQuery = true)
    public List<Post> findAllPostByUserIdsSortedByDateDesc(@Param("users") List<Integer> userId);

}
