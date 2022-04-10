package com.hit.kiemtraso1.services;

import com.hit.kiemtraso1.dto.DarlingDTO;
import com.hit.kiemtraso1.model.Address;
import com.hit.kiemtraso1.model.Darling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDarlingService {
    List<Darling> findAllDarling(Integer page);
    List<Darling> findAllDarlingCurrent();
    List<Darling> findAllDarlingByStatus(Long status);
    Darling findDarlingById(Long id);
    String createDarling(DarlingDTO darlingDTO);
    String updateDarling(Long id, DarlingDTO darlingDTO);
    String deleteDarling(Long id);
    List<Address> findAllAddressByIdDarling(Long id);
    List<Darling> findAllDarlingByAddress(Long id, String name);


}
