package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.exception.CommentException;
import com.insta.instagram.instagramapi.exception.PostException;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException;

    public Comment findCommentById(Integer commentId) throws CommentException;

    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException;

    public Comment unLikeComment(Integer commentId, Integer userId) throws CommentException, UserException;


}
