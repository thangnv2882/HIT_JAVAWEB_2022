package com.example.springsecurity_jwt.controllers;

import com.example.springsecurity_jwt.dto.UserDTO;
import com.example.springsecurity_jwt.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok().body(userService.findAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.createUser(userDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id) {
        return ResponseEntity.status(200).body(userService.deleteUser(id));
    }


}
