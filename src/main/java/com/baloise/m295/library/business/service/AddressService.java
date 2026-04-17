package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.AddressEntity;
import com.baloise.m295.library.common.CustomerEntity;
import com.baloise.m295.library.persistence.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for all addresses
 * 
 * @author Julian Schiller
 */

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository repo;
    private final CustomerService customerService;

    public List<AddressEntity> getAddresses(String address, String zip) {

        List<AddressEntity> entities;
        if (address != null) {
            entities = repo.findByAddress(address);
        } else if (zip != null) {
            entities = repo.findByZip(zip);
        } else {
            entities = repo.findAll();
        }

        return entities;
    }

    public void createAddress(AddressEntity address) {
        repo.save(address);
    }

    public void deleteAddress(long id) {

        AddressEntity address = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

        List<CustomerEntity> customerWithAddress = customerService.getCustomersWithFilter(null, id);

        if (!customerWithAddress.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address is still referenced by customers");
        }

        repo.delete(address);
    }

}
