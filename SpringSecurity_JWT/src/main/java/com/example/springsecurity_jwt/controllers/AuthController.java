package com.example.springsecurity_jwt.controllers;

import com.example.springsecurity_jwt.dto.UserDTO;
import com.example.springsecurity_jwt.payload.AuthenticationResponse;
import com.example.springsecurity_jwt.services.impl.MyUserDetailsService;
import com.example.springsecurity_jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUsername(), userDTO.getPassword()
                    )
            );
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(userDTO.getUsername());
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().body(new AuthenticationResponse(userDTO.getUsername(), jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }
}


