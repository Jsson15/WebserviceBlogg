package com.example.webservice.controller;





import com.example.webservice.DTO.PostDTO;
import com.example.webservice.entitie.Post;
import com.example.webservice.service.PostService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class PostController {
    @Autowired
    PostService postService;

    public PostController() {
    }

    @GetMapping({"/posts"})
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(this.postService.getAllPosts());
    }

    @GetMapping({"/posts/{id}"})
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.getPostById(id));
    }

    @PostMapping({"/newpost"})
    @PreAuthorize("hasRole('blogg_user')")
    public ResponseEntity<String> addNewPost(@RequestBody Post post, Principal principal) {
        this.postService.addNewPost(post, principal);
        return ResponseEntity.ok("Post posted on blogg");
    }

    @PutMapping({"/updatepost/{id}"})
    @PreAuthorize("hasRole('blogg_user')")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post, Principal principal) {
        return ResponseEntity.ok(this.postService.updatePost(id, post, principal));
    }

    @DeleteMapping({"/deletepost/{id}"})
    @PreAuthorize("hasRole('blogg_user')")
    public ResponseEntity<String> deletePost(@PathVariable Long id, Principal principal) {
        this.postService.deletePost(id, principal);
        return ResponseEntity.ok("Post with id:" + id + " deleted");
    }
}

