package com.insta.instagram.instagramapi.controller;

import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.response.MessageResponse;
import com.insta.instagram.instagramapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findUserByIdHandler(@PathVariable Integer id) throws UserException {
        User user = userService.findUserById(id);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsernameHandler(@PathVariable String username) throws UserException {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/follow/{userId}")
    public ResponseEntity<MessageResponse> followUserHandler(@PathVariable Integer userId) throws UserException {
//        MessageResponse response = userService.followUser();
      return null;
    }

    @PutMapping("/unfollow/{userId}")
    public ResponseEntity<MessageResponse> unfollowUserHandler(@PathVariable Integer userId) throws UserException {
//        MessageResponse response = userService.followUser();
        return null;
    }

    @PutMapping("/req")
    public ResponseEntity<MessageResponse> findUserProfileHandler(@RequestHeader("authorization") String token) throws UserException {
        return null;
    }

    @GetMapping("/m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIdsHandler(@PathVariable List<Integer> userIds) throws UserException {
        List<User> users = userService.findUsersByIds(userIds);

        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    // api/users.search?q="query"
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUserHandler(@RequestParam("q") String query) throws UserException {
        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }


    public ResponseEntity<User> updateUserHandler(@RequestHeader("authorization") String token, @RequestBody User user){
//        User requser =
//        User updateUser = userService.updateUserDetails(user,user);
        return null;
    }


}
