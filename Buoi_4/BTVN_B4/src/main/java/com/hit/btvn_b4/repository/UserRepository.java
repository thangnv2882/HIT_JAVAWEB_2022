package com.hit.btvn_b4.repository;

import com.hit.btvn_b4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
