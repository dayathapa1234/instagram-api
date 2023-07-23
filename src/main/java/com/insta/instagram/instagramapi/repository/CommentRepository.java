package com.insta.instagram.instagramapi.repository;

import com.insta.instagram.instagramapi.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
