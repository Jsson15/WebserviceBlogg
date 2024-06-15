package com.example.webservice.service;




import com.example.webservice.DTO.PostDTO;
import com.example.webservice.DTO.UserDTO;
import com.example.webservice.entitie.Post;
import com.example.webservice.entitie.User;
import com.example.webservice.exception.FalseUserException;
import com.example.webservice.exception.ResourceNotFoundException;
import com.example.webservice.repo.PostRepo;
import com.example.webservice.repo.UserRepo;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceInterface {
    @Autowired
    PostRepo postRepository;
    @Autowired
    UserRepo userRepository;

    public PostService() {
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        return (List)posts.stream().map(this::convertToPostDTO).collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        Post post = (Post)this.postRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Post", "id", id);
        });
        return this.convertToPostDTO(post);
    }

    public void addNewPost(Post post, Principal principal) {
        String loggedInUserName = principal.getName();
        if (this.userRepository.existsByUserName(loggedInUserName)) {
            User userForPost = this.userRepository.findByUserName(loggedInUserName);
            post.setUser(userForPost);
            post.setDateForPost(LocalDate.now());
            this.postRepository.save(post);
        } else {
            throw new ResourceNotFoundException("User", "username", loggedInUserName);
        }
    }

    public Post updatePost(Long postId, Post post, Principal principal) {
        String loggedInUserName = principal.getName();
        Post postToUpdate = (Post)this.postRepository.findById(postId).orElseThrow(() -> {
            return new ResourceNotFoundException("Post", "id", postId);
        });
        User userFromPostToUpdate = (User)this.userRepository.findById(postToUpdate.getUser().getId()).orElseThrow(() -> {
            return new ResourceNotFoundException("User", "id", postToUpdate.getUser().getId());
        });
        if (loggedInUserName.equals(userFromPostToUpdate.getUserName())) {
            postToUpdate.setPostTitle(post.getPostTitle());
            postToUpdate.setPostText(post.getPostText());
            postToUpdate.setDateForPost(LocalDate.now());
            return (Post)this.postRepository.save(postToUpdate);
        } else {
            throw new FalseUserException("update");
        }
    }

    public void deletePost(Long id, Principal principal) {
        Post postToDelete = (Post)this.postRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Post", "id", id);
        });
        if (postToDelete.getUser().getUserName().equals(principal.getName())) {
            this.postRepository.deleteById(id);
        } else {
            throw new FalseUserException("delete");
        }
    }

    private PostDTO convertToPostDTO(Post post) {
        UserDTO userDTO = new UserDTO(post.getUser().getUserName());
        return new PostDTO(post.getId(), post.getPostTitle(), post.getPostText(), post.getDateForPost(), userDTO);
    }
}

