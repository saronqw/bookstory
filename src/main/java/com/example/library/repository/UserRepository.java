package com.example.library.repository;

import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByUsername(String username);

    Optional<User> findByUsername(String username);

//    User findAllByUsername(String username);
    User getByUsername(String username);

}
