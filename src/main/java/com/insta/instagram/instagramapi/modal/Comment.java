package com.insta.instagram.instagramapi.modal;

import com.insta.instagram.instagramapi.dto.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="user_id")),
            @AttributeOverride(name="email", column=@Column(name="user_email"))
    })
    private UserDTO user;

    private String content;

    @Embedded
    @ElementCollection
    private Set<UserDTO> likedByUser = new HashSet<UserDTO>();

    private LocalDateTime createdAt;

    public Comment() {}

    public Comment(Integer id, UserDTO user, String content, Set<UserDTO> likedByUser, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.likedByUser = likedByUser;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<UserDTO> getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Set<UserDTO> likedByUser) {
        this.likedByUser = likedByUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", likedByUser=" + likedByUser +
                ", createdAt=" + createdAt +
                '}';
    }
}
