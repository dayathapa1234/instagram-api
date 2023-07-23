package com.insta.instagram.instagramapi.service;

import com.insta.instagram.instagramapi.dto.UserDTO;
import com.insta.instagram.instagramapi.exception.PostException;
import com.insta.instagram.instagramapi.exception.UserException;
import com.insta.instagram.instagramapi.modal.Post;
import com.insta.instagram.instagramapi.modal.User;
import com.insta.instagram.instagramapi.repository.PostRepository;
import com.insta.instagram.instagramapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createdPost(Post post, Integer userId) throws UserException {
        User user = userService.findUserById(userId);

        UserDTO userDTO= new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserImage(user.getImage());
        userDTO.setUsername(user.getUsername());

        post.setUser(userDTO);

        post.setCreatedAt(LocalDateTime.now());
        Post createPost = postRepository.save(post);
        return createPost;
    }

    @Override
    @Transactional
    public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post= findPostById(postId);
        User user = userService.findUserById(userId);
        if (post.getUser().getId().equals(user.getId())){
            postRepository.deleteById(post.getId());
            return "Post Deleted Successfully";
        }
        throw new PostException("Unable to delete this post");
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws UserException {
        List<Post> posts = postRepository.findByUserId(userId);

        if(posts.size() == 0){
            throw  new UserException("This user does not have any post");
        }

        return posts;
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()){
            return optionalPost.get();

        }
        throw new PostException("Post not found with the id "+ postId);
    }

    @Override
    public List<Post> findAllPostByUserIds(List<Integer> userIds) throws UserException, PostException {
        List<Post> postList = postRepository.findAllPostByUserIdsSortedByDateDesc(userIds);

        if(postList.size() == 0){
            throw new PostException("No post found");
        }
        return postList;
    }

    @Override
    public String savedPost(Integer postId, Integer userId) throws PostException, UserException {
        Post post= findPostById(postId);
        User user = userService.findUserById(userId);

        if (!user.getSavedPost().contains(post)) {
            user.getSavedPost().add(post);
            userRepository.save(user);
        }
        return "Post Saved Successfully";
    }

    @Override
    public String unSavedPost(Integer postId, Integer userId) throws PostException, UserException {
        Post post= findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
            userRepository.save(user);
        }
        return "Post Removed Successfully";
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws UserException, PostException {
        Post post= findPostById(postId);
        User user = userService.findUserById(userId);

        UserDTO userDTO= new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserImage(user.getImage());
        userDTO.setUsername(user.getUsername());

        post.getLikedByUser().add(userDTO);


        return postRepository.save(post);
    }

    @Override
    public Post unLikePost(Integer postId, Integer userId) throws UserException, PostException {

        Post post= findPostById(postId);
        User user = userService.findUserById(userId);

        UserDTO userDTO= new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUserImage(user.getImage());
        userDTO.setUsername(user.getUsername());

        post.getLikedByUser().remove(userDTO);

        return postRepository.save(post);
    }
}
