package com.example.webservice.service;




import com.example.webservice.DTO.PostDTO;
import com.example.webservice.entitie.Post;
import java.security.Principal;
import java.util.List;

public interface PostServiceInterface {
    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long id);

    void addNewPost(Post post, Principal principal);

    Post updatePost(Long id, Post post, Principal principal);

    void deletePost(Long id, Principal principal);
}

