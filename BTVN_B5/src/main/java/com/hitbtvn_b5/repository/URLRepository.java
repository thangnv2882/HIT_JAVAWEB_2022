package com.hitbtvn_b5.repository;

import com.hitbtvn_b5.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    URL findByURLId(Long id);

}
