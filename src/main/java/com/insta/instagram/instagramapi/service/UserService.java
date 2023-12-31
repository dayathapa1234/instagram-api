package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.dto.LoginResponseDTO;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;

import java.util.List;

public interface UserService{

    public User findUserById(Integer id) throws UserException;
    public User findUserProfile(String token) throws UserException;
    public User findUserByUsername(String username) throws UserException;
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException;
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException;
    public List<User> findUsersByIds(List<Integer> ids) throws UserException;
    public List<User> searchUser(String query) throws UserException;
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
}
