package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.dto.UserDTO;
import com.insta.instagram.instagramapi.exception.CommentException;
import com.insta.instagram.instagramapi.exception.PostException;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Comment;
import com.insta.instagram.instagramapi.modal.Post;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.CommentRepository;
import com.insta.instagram.instagramapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws UserException, PostException {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserImage(user.getImage());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        comment.setUser(userDTO);
        comment.setCreatedAt(LocalDateTime.now());

        Comment createdComment = commentRepository.save(comment);

        post.getComments().add(createdComment);

        postRepository.save(post);

        return createdComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws CommentException {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()){
            return optionalComment.get();
        }
        throw new CommentException("Comment not found with id: " + commentId);
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException {

        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserImage(user.getImage());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        comment.getLikedByUser().add(userDTO);

        return commentRepository.save(comment);
    }

    @Override
    public Comment unLikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUserImage(user.getImage());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        comment.getLikedByUser().remove(userDTO);

        return commentRepository.save(comment);
    }
}
