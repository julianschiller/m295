package com.baloise.m295.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.AddressService;
import com.baloise.m295.library.common.AddressEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @GetMapping
    public List<AddressEntity> getAddresses(@RequestParam(required=false) String address,
            @RequestParam(required=false) String zip) {
        return service.getAddresses(address, zip);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress(@RequestBody AddressEntity address) {
        service.createAddress(address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable long id) {
        service.deleteAddress(id);
    }
}
