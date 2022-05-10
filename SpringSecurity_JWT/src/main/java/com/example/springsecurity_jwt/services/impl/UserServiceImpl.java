package com.example.springsecurity_jwt.services.impl;

import com.example.springsecurity_jwt.dao.User;
import com.example.springsecurity_jwt.dto.UserDTO;
import com.example.springsecurity_jwt.exception.NotFoundException;
import com.example.springsecurity_jwt.repositories.IUserRepository;
import com.example.springsecurity_jwt.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return "Created";

    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserExists(user);
        return user.get();
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        checkUserExists(user);
        userRepository.delete(user.get());
        return "Deleted";
    }


    public void checkUserExists(Optional<User> user) {
        if (user.isEmpty()) {
            throw new NotFoundException("Couldn't find a user.");
        }
    }

}
