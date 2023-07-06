package com.insta.instagram.instagramapi.modal;

import com.insta.instagram.instagramapi.dto.UserDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String name;
    private String email;
    private Integer mobile;
    private String website;
    private String bio;
    private String gender;
    private String image;
    private String password;

    @Embedded
    @ElementCollection
    private Set<UserDTO> follower = new HashSet<UserDTO>();
    @Embedded
    @ElementCollection
    private Set<UserDTO> following = new HashSet<UserDTO>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories = new ArrayList<>();

    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String username, String name, String email, Integer mobile, String website, String bio, String gender, String image, String password, Set<UserDTO> follower, Set<UserDTO> following, List<Story> stories, List<Post> savedPost) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.website = website;
        this.bio = bio;
        this.gender = gender;
        this.image = image;
        this.password = password;
        this.follower = follower;
        this.following = following;
        this.stories = stories;
        this.savedPost = savedPost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserDTO> getFollower() {
        return follower;
    }

    public void setFollower(Set<UserDTO> follower) {
        this.follower = follower;
    }

    public Set<UserDTO> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserDTO> following) {
        this.following = following;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", follower=" + follower +
                ", following=" + following +
                ", stories=" + stories +
                ", savedPost=" + savedPost +
                '}';
    }
}
