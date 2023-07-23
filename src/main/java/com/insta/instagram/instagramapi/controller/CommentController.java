package com.insta.instagram.instagramapi.controller;

import com.insta.instagram.instagramapi.exception.CommentException;
import com.insta.instagram.instagramapi.exception.PostException;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Comment;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.service.CommentService;
import com.insta.instagram.instagramapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment,@RequestHeader("Authorization") String token, @PathVariable("postId") Integer postId) throws UserException, PostException {
        User user = userService.findUserProfile(token);

        Comment createdComment = commentService.createComment(comment,postId,user.getId());

        return new ResponseEntity<Comment>(createdComment, HttpStatus.OK);
    }

    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable("commentId") Integer commentId) throws UserException, CommentException {
        User user = userService.findUserProfile(token);

        Comment comment = commentService.likeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }

    @PutMapping("/unlike/{commentId}")
    public ResponseEntity<Comment> unLikeCommentHandler(@RequestHeader("Authorization") String token, @PathVariable("commentId") Integer commentId) throws UserException, CommentException {
        User user = userService.findUserProfile(token);

        Comment comment = commentService.unLikeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment,HttpStatus.OK);
    }
}
