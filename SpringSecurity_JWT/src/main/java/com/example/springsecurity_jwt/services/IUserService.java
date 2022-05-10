package com.example.springsecurity_jwt.services;

import com.example.springsecurity_jwt.dao.User;
import com.example.springsecurity_jwt.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAllUser();
    String createUser(UserDTO userDTO);
    User findUserById(Long id);
    String deleteUser(Long id);
}
