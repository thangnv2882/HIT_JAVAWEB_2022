package com.hit.kiemtraso1.services;

import com.hit.kiemtraso1.dto.AddressDTO;
import com.hit.kiemtraso1.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAddressService {
    List<Address> findAllAddress(Integer page);
    Address findAddressById(Long id);
    String createAddress(Long darlingId, AddressDTO addressDTO);
    String updateAddress(Long darlingId, Long id, AddressDTO addressDTO);
    String deleteAddress(Long darlingId, Long id);
}
