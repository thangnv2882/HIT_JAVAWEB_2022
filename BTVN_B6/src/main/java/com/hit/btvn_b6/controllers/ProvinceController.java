package com.hit.btvn_b6.controllers;

import com.github.slugify.Slugify;
import com.hit.btvn_b6.dto.ProvinceDTO;
import com.hit.btvn_b6.exceptions.DuplicateException;
import com.hit.btvn_b6.exceptions.NotFoundException;
import com.hit.btvn_b6.model.District;
import com.hit.btvn_b6.model.Province;
import com.hit.btvn_b6.repository.ProvinceRepository;
import com.hit.btvn_b6.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Slugify slg = new Slugify();

    // Lấy tất các thông tin tỉnh/thành phố
    @GetMapping
    public ResponseEntity<?> getAllProvince(
            @RequestParam(value = "page", required = false) Integer page
    ) {
        List<Province> provinces;
        if(page == null) {
            provinces = provinceRepository.findAll(Sort.by("code").ascending());
        } else {
            provinces = provinceRepository.findAll(PageRequest.of(page, 3, Sort.by("code").ascending())).getContent();
        }

        return ResponseEntity.status(200).body(provinces);


    }

    // Thêm thông tin của tỉnh/thành phố
    @PostMapping
    public ResponseEntity<?> createProvince(@RequestBody ProvinceDTO provinceDTO) {
        Province province = provinceService.findByCode(provinceDTO.getCode());

        if (province != null) {
            throw new DuplicateException("Province already exists.");
        }
        province = modelMapper.map(provinceDTO, Province.class);
        province.setSlug(slg.slugify(provinceDTO.getName()));

        provinceService.save(province);
        return ResponseEntity.status(200).body("Created");
    }

    // Lấy thông tin của tỉnh/thành phố theo mã tỉnh/thành phố
    @GetMapping("/{code}")
    public ResponseEntity<?> getProvinceById(@PathVariable("code") Long code) {
        Province province = provinceService.findByCode(code);
        if (province == null) {
            throw new NotFoundException("Can't find a province with code: " + code);
        }
        return ResponseEntity.status(200).body(province);
    }

    // Sửa thông tin của tỉnh/thành phố theo mã tỉnh/thành phố
    @PatchMapping("/{code}")
    public ResponseEntity<?> editProvinceByCode(@PathVariable("code") Long code, @RequestBody ProvinceDTO provinceDTO) {
        Province province = provinceService.findByCode(code);
        if (province == null) {
            throw new NotFoundException("Can't find a province with code: " + code);
        }
        province = modelMapper.map(provinceDTO, Province.class);
        province.setSlug(slg.slugify(provinceDTO.getName()));

        provinceService.save(province);
        return ResponseEntity.status(200).body("Updated");
    }

    // Xóa thông tin của tỉnh/thành phố theo mã tỉnh/thành phố
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteProvinceByCode(@PathVariable("code") Long code) {
        Province province = provinceService.findByCode(code);
        if (province == null) {
            throw new NotFoundException("Can't find a province with code: " + code);
        }
        provinceService.deleteByCode(code);
        return ResponseEntity.status(200).body("Deleted");
    }

    // Lấy các thông tin quận/huyện của tỉnh/thành phố theo mã tỉnh/thành phố
    @GetMapping("/{code}/districts")
    public ResponseEntity<?> getAllDistrictByCode(@PathVariable("code") Long code) {
        List<District> districts = provinceService.findByCode(code).getDistricts();
        return ResponseEntity.status(200).body(districts);
    }

}
