package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;

import java.util.List;

public class UserServiceImplementation implements UserService{

    @Override
    public User registerUser(User user) throws UserException {

        return null;
    }

    @Override
    public User findUserById(Integer id) throws UserException {
        return null;
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        return null;
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
        return null;
    }

    @Override
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        return null;
    }

    @Override
    public List<User> findUsersByIds(List<Integer> ids) throws UserException {
        return null;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return null;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }
}
