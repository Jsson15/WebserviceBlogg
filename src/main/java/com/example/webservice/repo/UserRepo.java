package com.example.webservice.repo;




import com.example.webservice.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    User findByUserName(String userName);
}

