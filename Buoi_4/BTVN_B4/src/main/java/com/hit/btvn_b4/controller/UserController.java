package com.hit.btvn_b4.controller;

import com.hit.btvn_b4.dto.UserDTO;
import com.hit.btvn_b4.exception.NotFoundException;
import com.hit.btvn_b4.model.User;
import com.hit.btvn_b4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.hit.btvn_b4.dto.UserDTO.checkNull;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Not found user with id: " + id);
        }
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserDTO userDTO) {
        User user = new User();

        if (checkNull(userDTO)) {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setFullname(userDTO.getFullname());

            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Created.");
        }
        throw new NotFoundException("Create failure.");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> editUserById(
            @PathVariable("id") Long id,
            @RequestBody UserDTO userDTO
    ) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Not found user with id: " + id);
        }
        user.get().setFullname(userDTO.getFullname());
        user.get().setUsername(userDTO.getUsername());
        user.get().setPassword(userDTO.getPassword());

        userRepository.save(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Not found user with id: " + id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

}
