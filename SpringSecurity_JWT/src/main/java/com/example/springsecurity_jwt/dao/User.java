package com.example.springsecurity_jwt.dao;

import com.example.springsecurity_jwt.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Nationalized
    @NotBlank
    private String fullname;
}
