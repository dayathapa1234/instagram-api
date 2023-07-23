package com.insta.instagram.instagramapi.modal;

import com.insta.instagram.instagramapi.dto.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;

    private String image;
    private String location;
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="user_id")),
            @AttributeOverride(name="email", column =@Column(name="user_email"))
    })
    private UserDTO user;

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    @Embedded
    @ElementCollection
    @JoinTable(name="likedByUsers")
    private Set<UserDTO> likedByUser = new HashSet<UserDTO>();


    public Post(){};

    public Post(Integer id, String caption, String image, String location, LocalDateTime createdAt, UserDTO user, List<Comment> comments, Set<UserDTO> likedByUser) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.location = location;
        this.createdAt = createdAt;
        this.user = user;
        this.comments = comments;
        this.likedByUser = likedByUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<UserDTO> getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Set<UserDTO> likedByUser) {
        this.likedByUser = likedByUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", image='" + image + '\'' +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", comments=" + comments +
                ", likedByUser=" + likedByUser +
                '}';
    }
}
