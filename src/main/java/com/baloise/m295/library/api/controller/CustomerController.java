package com.baloise.m295.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.CustomerService;
import com.baloise.m295.library.common.CustomerEntity;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/library/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/{id}") 
    public CustomerEntity getCustomerById(@PathVariable long id) {
        return service.getCustomer(id);
    }

    @GetMapping
    public List<CustomerEntity> getCustomers(@RequestParam(required=false) String name, @RequestParam(required=false) Long addressId) {
        return service.getCustomersWithFilter(name, addressId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerEntity customer){
        service.createNewCustomer(customer);
    }

    @PatchMapping("/{id}")
    public void editCustomer(@RequestBody CustomerEntity customer, @PathVariable long id) {
        service.editCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable long id) {
        service.deleteCustomer(id);
    }
    

}
