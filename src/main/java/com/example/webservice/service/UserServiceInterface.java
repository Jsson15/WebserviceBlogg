package com.example.webservice.service;





import com.example.webservice.entitie.User;
import java.util.List;

public interface UserServiceInterface {
    void createUser(User user);

    List<User> getAllUsers();

    User getUserById(long id);
}
