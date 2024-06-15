package com.example.webservice.controller;




import com.example.webservice.entitie.User;
import com.example.webservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class UserController {
    @Autowired
    UserService userService;

    public UserController() {
    }

    @GetMapping({"/users"})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping({"/users/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PostMapping({"/newuser"})
    public ResponseEntity<String> newUser(@RequestBody User user) {
        this.userService.createUser(user);
        return ResponseEntity.ok("New user created");
    }
}

