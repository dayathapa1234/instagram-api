package com.insta.instagram.instagramapi.controller;

import com.insta.instagram.instagramapi.exception.PostException;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Post;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.response.MessageResponse;
import com.insta.instagram.instagramapi.service.PostService;
import com.insta.instagram.instagramapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    private ResponseEntity<Post> createPostHandler(@RequestBody Post post,@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        Post createdPost = postService.createdPost(post,user.getId());

        return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable("id") Integer userId) throws UserException {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/following/{ids}")
    public ResponseEntity<List<Post>> findPostByUserIdsHandler(@PathVariable("ids") List<Integer> userIds) throws UserException, PostException {
        List<Post> posts = postService.findAllPostByUserIds(userIds);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws PostException {

        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.OK);
    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Post likedPost = postService.likePost(postId,user.getId());

        return new ResponseEntity<Post>(likedPost, HttpStatus.OK);
    }

    @PutMapping("/unlike/{postId}")
    public ResponseEntity<Post> unlikePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);
        Post unLikedPost = postService.unLikePost(postId,user.getId());

        return new ResponseEntity<Post>(unLikedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    private ResponseEntity<MessageResponse> deletePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);

        String message = postService.deletePost(postId,user.getId());
        MessageResponse messageResponse = new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse,HttpStatus.OK);
    }

    @PutMapping("/save_post/{postId}")
    public ResponseEntity<MessageResponse> savePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws UserException, PostException {
        User user = userService.findUserProfile(token);

        String message = postService.savedPost(postId,user.getId());
        MessageResponse messageResponse = new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse,HttpStatus.OK);
    }

    @PutMapping("/unsave_post/{postId}")
    public ResponseEntity<MessageResponse> unSavePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token ) throws UserException, PostException {
        User user = userService.findUserProfile(token);

        String message = postService.unSavedPost(postId,user.getId());
        MessageResponse messageResponse = new MessageResponse(message);

        return new ResponseEntity<MessageResponse>(messageResponse,HttpStatus.OK);
    }
}
