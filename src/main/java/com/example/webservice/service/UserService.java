package com.example.webservice.service;





import com.example.webservice.entitie.User;
import com.example.webservice.exception.ResourceNotFoundException;
import com.example.webservice.repo.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepo userRepository;

    public UserService() {
    }

    public void createUser(User user) {
        if (!this.userRepository.existsByUserName(user.getUserName())) {
            this.userRepository.save(user);
        } else {
            throw new RuntimeException("User with username: " + user.getUserName() + " already exist");
        }
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(long id) {
        return (User)this.userRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User", "id", id);
        });
    }
}

