package com.baloise.m295.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baloise.m295.library.business.service.BorrowingService;
import com.baloise.m295.library.common.BorrowingEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/library/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService service;

    @GetMapping
    public List<BorrowingEntity> getAllBorrowings(){
        return service.getAllBorrowings();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBorrowing(@RequestBody BorrowingEntity borrowing) {
        service.createBorrowing(borrowing);
    }

    @PatchMapping("/{id}")
    public void extendBorrowing(@RequestBody BorrowingEntity borrowing, @PathVariable long id) {
        service.extendBorrowing(id, borrowing.getDuration());
    }

    @DeleteMapping
    public void deleteBorrowing(@RequestParam int mediaId) {
        service.endBorrowing(mediaId);
    }
}
