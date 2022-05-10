package com.example.buoi10.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    @Email(message = "Khong dung dinh dang")
    private String email;

    @NotBlank(message = "Khong duoc de trong")
    private String favorite;
}
