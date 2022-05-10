package com.hit.kiemtraso1.repositories;

import com.hit.kiemtraso1.model.Darling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DarlingRepository extends JpaRepository<Darling, Long> {

    List<Darling> findAllByStatusBetween(Long status1, Long status2);

    List<Darling> findAllByStatus(Long status);
}
