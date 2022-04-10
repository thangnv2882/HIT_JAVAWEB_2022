package com.hit.kiemtraso1.controllers;

import com.hit.kiemtraso1.dto.AddressDTO;
import com.hit.kiemtraso1.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private IAddressService iAddressService;

    @GetMapping
    public ResponseEntity<?> findAllAddress(@RequestParam(value = "page", required = false) Integer page) {
        return ResponseEntity.status(200).body(iAddressService.findAllAddress(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iAddressService.findAddressById(id));
    }

    @PostMapping("/{darlingId}")
    public ResponseEntity<?> createAddress(@PathVariable("darlingId") Long darlingId, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(200).body(iAddressService.createAddress(darlingId, addressDTO));
    }

    @PatchMapping("/{darlingId}/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(200).body(iAddressService.updateAddress(darlingId, id, addressDTO));
    }

    @DeleteMapping("/{darlingId}/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("darlingId") Long darlingId, @PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iAddressService.deleteAddress(darlingId, id));
    }
}
