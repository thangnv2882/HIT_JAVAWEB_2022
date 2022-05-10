package com.example.springsecurity_jwt.repositories;

import com.example.springsecurity_jwt.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
