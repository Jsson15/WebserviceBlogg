package com.example.webservice.entitie;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String postTitle;
    @Column(
            nullable = false
    )
    private String postText;
    @Column(
            nullable = false
    )
    private LocalDate dateForPost;
    @JsonIgnoreProperties({"posts"})
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    public Post() {
    }

    public Post(String postTitle, String postText, LocalDate dateForPost, User user) {
        this.postTitle = postTitle;
        this.postText = postText;
        this.dateForPost = dateForPost;
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getPostTitle() {
        return this.postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return this.postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public LocalDate getDateForPost() {
        return this.dateForPost;
    }

    public void setDateForPost(LocalDate dateForPost) {
        this.dateForPost = dateForPost;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

