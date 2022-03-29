package com.hit.btvn_b6.repository;


import com.hit.btvn_b6.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByCode(Long code);
}
