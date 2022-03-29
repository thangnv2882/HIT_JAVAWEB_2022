package com.hit.btvn_b6.service;

import com.hit.btvn_b6.model.Province;
import com.hit.btvn_b6.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public void save(Province province) {
        provinceRepository.save(province);
    }
    public Province findByCode(Long id) {
        return provinceRepository.findByCode(id);
    }
    public void deleteByCode(Long code) {
        provinceRepository.deleteById(code);
    }

}
