package com.baloise.m295.library.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.CustomerEntity;
import com.baloise.m295.library.persistence.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    /**
     * @param id
     * @return
     */
    public CustomerEntity getCustomer(long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public List<CustomerEntity> getCustomersWithFilter(String name, Long addressId) {
        
        List <CustomerEntity> customers;
        
        if (name != null) {
            customers = repo.findByLastname(name);
        } else if (addressId != null) {
            return repo.findByAddress_Id(addressId);
        } else {
            customers = new ArrayList<>();
        }

        return customers;
    }

    public void createNewCustomer(CustomerEntity customer) {
        repo.save(customer);
    }

    public void editCustomer(long id, CustomerEntity updatedCustomer) {
        CustomerEntity oldCustomer = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        oldCustomer = mergeCustomers(updatedCustomer, oldCustomer);

        repo.save(oldCustomer);
    }


    private CustomerEntity mergeCustomers(CustomerEntity newEntity, CustomerEntity oldEntity) {

        if (newEntity.getEmail() != null) {
            oldEntity.setEmail(newEntity.getEmail());
        }
    
        if (newEntity.getAddress() != null) {
            oldEntity.setAddress(newEntity.getAddress());
        }


        return oldEntity;
    }

    public void deleteCustomer(long id) {
        CustomerEntity customer = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        repo.delete(customer);
    }
}
