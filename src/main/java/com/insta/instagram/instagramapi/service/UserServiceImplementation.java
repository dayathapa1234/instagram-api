package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.dto.UserDTO;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(User user) throws UserException {

        Optional<User> doesEmailExistInDB = userRepository.findByEmail(user.getEmail());
        Optional<User> doesUsernameExistInDB = userRepository.findByUsername(user.getUsername());

        if(doesEmailExistInDB.isPresent()){
            throw new UserException("Email already exist");
        }

        if (doesUsernameExistInDB.isPresent()) {
            throw new UserException("Username is already taken");
        }

        if(user.getEmail()==null || user.getUsername()==null || user.getPassword()==null || user.getName()==null){
            throw new UserException("All fields are required");
        }
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());

        return userRepository.save(newUser);
    }
    @Override
    public User findUserById(Integer id) throws UserException {
        Optional<User> userInDB = userRepository.findById(id);

        if (userInDB.isPresent()){
            return userInDB.get();
        }

        throw new UserException("User does not exist with id: "+id);
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {

        Optional<User> optUser = userRepository.findByUsername(username);
        if(optUser.isPresent()){
            return optUser.get();
        }
        throw new UserException("User does not exist with username: "+username);
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDTO follower = new UserDTO();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDTO following = new UserDTO();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setName(follower.getName());
        following.setUserImage(follower.getUserImage());
        following.setUsername(follower.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "You are following "+ followUser.getUsername();
    }

    @Override
    public String unfollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDTO follower = new UserDTO();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUsername(reqUser.getUsername());

        UserDTO following = new UserDTO();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setName(follower.getName());
        following.setUserImage(follower.getUserImage());
        following.setUsername(follower.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollower().remove(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "You are unfollowing "+ followUser.getUsername();
    }

    @Override
    public List<User> findUsersByIds(List<Integer> ids) throws UserException {
        List<User> users = userRepository.findAllUsersByUserIds(ids);
        return users;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        List<User> users = userRepository.findByQuery(query);
        if (users.size() == 0){
            throw new UserException("User not found");
        }
        return users;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        if(updatedUser.getEmail()!=null){
            existingUser.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getUsername()!=null){
            existingUser.setUsername(updatedUser.getUsername());
        }
        if(updatedUser.getBio()!=null){
            existingUser.setBio(updatedUser.getBio());
        }
        if(updatedUser.getName()!=null){
            existingUser.setName(updatedUser.getName());
        }
        if(updatedUser.getMobile()!=null){
            existingUser.setMobile(updatedUser.getMobile());
        }
        if(updatedUser.getGender()!=null){
            existingUser.setGender(updatedUser.getGender());
        }
        if(updatedUser.getWebsite()!=null){
            existingUser.setWebsite(updatedUser.getWebsite());
        }
        if(updatedUser.getImage()!=null){
            existingUser.setImage(updatedUser.getImage());
        }
        if(updatedUser.getId().equals(existingUser.getId())){
            return userRepository.save(existingUser);
        }
        throw new UserException("You cannot update this user");
    }
}
