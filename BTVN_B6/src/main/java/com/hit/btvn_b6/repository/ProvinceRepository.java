package com.hit.btvn_b6.repository;


import com.hit.btvn_b6.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findByCode(Long code);
}
