package com.hit.btvn_b6.controllers;

import com.github.slugify.Slugify;
import com.hit.btvn_b6.dto.DistrictDTO;
import com.hit.btvn_b6.dto.ProvinceDTO;
import com.hit.btvn_b6.exceptions.DuplicateException;
import com.hit.btvn_b6.exceptions.NotFoundException;
import com.hit.btvn_b6.model.District;
import com.hit.btvn_b6.model.Province;
import com.hit.btvn_b6.service.DistrictService;
import com.hit.btvn_b6.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ModelMapper modelMapper;

    private Slugify slg = new Slugify();

    @PostMapping("/province-collection")
    public ResponseEntity<?> addListProvince(@RequestBody List<ProvinceDTO> provinceDTOs) {

        for (ProvinceDTO p : provinceDTOs) {
            Province province = provinceService.findByCode(p.getCode());
            if (province != null) {
                throw new DuplicateException("Province " + p.getName() + " already exists.");
            }

            province = modelMapper.map(p, Province.class);
            province.setSlug(slg.slugify(p.getName()));

            provinceService.save(province);
        }
        return ResponseEntity.status(200).body("Created list province.");
    }

    @PostMapping("/district-collection")
    public ResponseEntity<?> addListDistrict(@RequestBody List<DistrictDTO> districtDTOS) {

        for (DistrictDTO d : districtDTOS) {
            District district = districtService.findByCode(d.getCode());
            Province province = provinceService.findByCode(d.getParent_code());
            if (district != null) {
                throw new DuplicateException("District " + d.getName() + " already exists.");
            }
            if (province == null) {
                throw new NotFoundException("Can't find a province with id: " + d.getParent_code());
            }

            district = modelMapper.map(d, District.class);
            district.setSlug(slg.slugify(d.getName()));
            district.setProvince(province);

            districtService.save(district);
        }
        return ResponseEntity.status(200).body("Created list district.");
    }

}
