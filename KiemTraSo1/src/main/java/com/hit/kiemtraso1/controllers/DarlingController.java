package com.hit.kiemtraso1.controllers;

import com.hit.kiemtraso1.dto.DarlingDTO;
import com.hit.kiemtraso1.services.IDarlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/darlings")
public class DarlingController {

    @Autowired
    private IDarlingService iDarlingService;

    @GetMapping
    public ResponseEntity<?> getAllDarling(@RequestParam(value = "page", required = false) Integer page) {
        return ResponseEntity.status(200).body(iDarlingService.findAllDarling(page));
    }

    @GetMapping("/current")
    public ResponseEntity<?> getAllDarlingCurrent() {
        return ResponseEntity.status(200).body(iDarlingService.findAllDarlingCurrent());
    }

    @GetMapping("/find")
    public ResponseEntity<?> getAllDarlingByStatus(@RequestParam(value = "status") Long status) {
        return ResponseEntity.status(200).body(iDarlingService.findAllDarlingByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDarlingById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iDarlingService.findDarlingById(id));
    }

    @PostMapping
    public ResponseEntity<?> createDarling(@RequestBody DarlingDTO darlingDTO) {
        return ResponseEntity.status(200).body(iDarlingService.createDarling(darlingDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDarling(@PathVariable("id") Long id, @RequestBody DarlingDTO darlingDTO) {
        return ResponseEntity.status(200).body(iDarlingService.updateDarling(id, darlingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDarling(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iDarlingService.deleteDarling(id));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<?> getAllAddressByIdDarling(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iDarlingService.findAllAddressByIdDarling(id));
    }

    @GetMapping("/{id}/addresses/name")
    public ResponseEntity<?> getAllDarlingByAddress(@PathVariable("id") Long id, @RequestParam(name = "q") String name) {
        return ResponseEntity.status(200).body(iDarlingService.findAllDarlingByAddress(id, name));
    }

}
