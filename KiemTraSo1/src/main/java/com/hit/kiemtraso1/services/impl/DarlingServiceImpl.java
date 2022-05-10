package com.hit.kiemtraso1.services.impl;

import com.hit.kiemtraso1.dto.DarlingDTO;
import com.hit.kiemtraso1.exceptions.NotFoundException;
import com.hit.kiemtraso1.model.Address;
import com.hit.kiemtraso1.model.Darling;
import com.hit.kiemtraso1.repositories.DarlingRepository;
import com.hit.kiemtraso1.services.IDarlingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DarlingServiceImpl implements IDarlingService {

    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Darling> findAllDarling(Integer page) {
        if (page == null) {
            return darlingRepository.findAll();
        } else {
            return darlingRepository.findAll(PageRequest.of(Math.toIntExact(page), 5)).getContent();
        }
    }

    @Override
    public List<Darling> findAllDarlingCurrent() {
        return darlingRepository.findAllByStatusBetween(1L, 2L);
    }

    @Override
    public List<Darling> findAllDarlingByStatus(Long status) {
        return darlingRepository.findAllByStatus(status);
    }


    @Override
    public Darling findDarlingById(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        return darling.get();
    }

    @Override
    public String createDarling(DarlingDTO darlingDTO) {
        Darling darling = modelMapper.map(darlingDTO, Darling.class);
        darlingRepository.save(darling);
        return "Created";
    }

    @Override
    public String updateDarling(Long id, DarlingDTO darlingDTO) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        modelMapper.map(darlingDTO, darling.get());
        darlingRepository.save(darling.get());
        return "Updated";
    }

    @Override
    public String deleteDarling(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        darling.get().setStatus(3L);
        darlingRepository.save(darling.get());
        return "Deleted";
    }

    @Override
    public List<Address> findAllAddressByIdDarling(Long id) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        return darling.get().getAddresses();
    }

    @Override
    public List<Darling> findAllDarlingByAddress(Long id, String name) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        List<Address> addresses = darling.get().getAddresses();
        List<Darling> darlings = new ArrayList<>();
        for (Address address : addresses) {
            if (address.getName().compareTo(name) == 0) {
                darlings.add(address.getDarling());
            }
        }
        return darlings;
    }


    public void checkDarlingExists(Optional<Darling> darling) {
        if (darling.isEmpty()) {
            throw new NotFoundException("Couldn't find a darling.");
        }
    }


}
