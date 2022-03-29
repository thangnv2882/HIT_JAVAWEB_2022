package com.hit.btvn_b6.controllers;

import com.github.slugify.Slugify;
import com.hit.btvn_b6.dto.DistrictDTO;
import com.hit.btvn_b6.exceptions.DuplicateException;
import com.hit.btvn_b6.exceptions.NotFoundException;
import com.hit.btvn_b6.model.District;
import com.hit.btvn_b6.model.Province;
import com.hit.btvn_b6.service.DistrictService;
import com.hit.btvn_b6.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ModelMapper modelMapper;

    private Slugify slg = new Slugify();

    // Lấy tất các thông tin quận/huyện
    @GetMapping
    public ResponseEntity<?> getAllDistrict() {
        List<District> districts = districtService.findAllDistricts();
        return ResponseEntity.status(200).body(districts);
    }

    // Thêm thông tin của quận/huyện
    @PostMapping
    private ResponseEntity<?> addDistrict(
            @RequestBody DistrictDTO districtDTO) {

        District district = districtService.findByCode(districtDTO.getCode());
        Province province = provinceService.findByCode(districtDTO.getParent_code());

        if (district != null) {
            throw new DuplicateException("District " + district.getName() + " already exists.");
        }
        if (province == null) {
            throw new NotFoundException("Can't find a province with id: " + districtDTO.getParent_code());
        }

        district = modelMapper.map(districtDTO, District.class);
        district.setSlug(slg.slugify(districtDTO.getName()));
        district.setProvince(province);

        districtService.save(district);
        return ResponseEntity.status(200).body("Created");
    }

    // Lấy thông tin của quận/huyện theo mã quận/huyện
    @GetMapping("/{code}")
    public ResponseEntity<?> getDistrictByCode(@PathVariable("code") Long code) {
        District district = districtService.findByCode(code);
        if (district == null) {
            throw new NotFoundException("Can't find a district with code: " + code);
        }
        return ResponseEntity.status(200).body(district);
    }

    // Sửa thông tin của quận/huyện theo mã quận/huyện
    @PatchMapping("/{code}")
    public ResponseEntity<?> updateDistrictByCode(@PathVariable("code") Long code, @RequestBody DistrictDTO districtDTO) {
        District district = districtService.findByCode(code);
        if (district == null) {
            throw new NotFoundException("Can't find a district with id: " + code);
        }
        Province province = provinceService.findByCode(districtDTO.getParent_code());

        district = modelMapper.map(districtDTO, District.class);
        district.setSlug(slg.slugify(districtDTO.getName()));
        district.setProvince(province);

        districtService.save(district);
        return ResponseEntity.status(200).body("Updated");
    }

    // Xóa thông tin của quận/huyện theo mã quận/huyện
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteDistrictByCode(@PathVariable("code") Long code) {
        District district = districtService.findByCode(code);
        if (district == null) {
            throw new NotFoundException("Can't find a district with id: " + code);
        }
        districtService.deleteByCode(code);
        return ResponseEntity.status(200).body("Deleted");
    }


}
