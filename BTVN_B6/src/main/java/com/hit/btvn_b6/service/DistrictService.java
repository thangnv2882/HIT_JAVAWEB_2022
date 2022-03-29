package com.hit.btvn_b6.service;

import com.hit.btvn_b6.model.District;
import com.hit.btvn_b6.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> findAllDistricts() {
        return districtRepository.findAll();
    }

    public void save(District district) {
        districtRepository.save(district);
    }

    public District findByCode(Long code) {
        return districtRepository.findByCode(code);
    }

    public void deleteByCode(Long code) {
        districtRepository.deleteById(code);
    }

}
