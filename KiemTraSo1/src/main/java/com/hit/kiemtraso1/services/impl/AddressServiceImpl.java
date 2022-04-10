package com.hit.kiemtraso1.services.impl;

import com.hit.kiemtraso1.dto.AddressDTO;
import com.hit.kiemtraso1.exceptions.NotFoundException;
import com.hit.kiemtraso1.model.Address;
import com.hit.kiemtraso1.model.Darling;
import com.hit.kiemtraso1.repositories.AddressRepository;
import com.hit.kiemtraso1.repositories.DarlingRepository;
import com.hit.kiemtraso1.services.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DarlingRepository darlingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Address> findAllAddress(Integer page) {
        if(page == null) {
            return addressRepository.findAll();
        } else {
            return addressRepository.findAll(PageRequest.of(Math.toIntExact(page), 5)).getContent();
        }
    }

    @Override
    public Address findAddressById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        checkAddressExists(address);
        return address.get();
    }

    @Override
    public String createAddress(Long id, AddressDTO addressDTO) {
        Optional<Darling> darling = darlingRepository.findById(id);
        checkDarlingExists(darling);
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setDarling(darling.get());
        addressRepository.save(address);
        return "Created";
    }

    @Override
    public String updateAddress(Long darlingId, Long id, AddressDTO addressDTO) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingExists(darling);
        Optional<Address> address = addressRepository.findById(id);
        checkAddressExists(address);
        modelMapper.map(addressDTO, address.get());
        addressRepository.save(address.get());
        return "Updated";
    }

    @Override
    public String deleteAddress(Long darlingId, Long id) {
        Optional<Darling> darling = darlingRepository.findById(darlingId);
        checkDarlingExists(darling);
        Optional<Address> address = addressRepository.findById(id);
        checkAddressExists(address);
        addressRepository.delete(address.get());
        return "Deleted";
    }


    public void checkDarlingExists(Optional<Darling> darling ){
        if(darling.isEmpty()) {
            throw new NotFoundException("Couldn't find a darling.");
        }
    }
    public void checkAddressExists(Optional<Address> address ){
        if(address.isEmpty()) {
            throw new NotFoundException("Couldn't find a address.");
        }
    }
}
