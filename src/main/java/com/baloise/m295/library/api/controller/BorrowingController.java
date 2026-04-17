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

/**
 * REST controller for borrowing endpoints
 * Handles HTTP requests for borrowing operations
 *
 * @author Julian Schiller
 */
@RestController
@RequestMapping("/library/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService service;

    /**
     * Retrieves all borrowings
     *
     * @return list of all borrowings
     */
    @GetMapping
    public List<BorrowingEntity> getAllBorrowings() {
        return service.getAllBorrowings();
    }

    /**
     * Creates a new borrowing
     *
     * @param borrowing borrowing data to create
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBorrowing(@RequestBody BorrowingEntity borrowing) {
        service.createBorrowing(borrowing);
    }

    /**
     * Extends an existing borrowing
     *
     * @param borrowing borrowing data containing new duration
     * @param id borrowing ID
     */
    @PatchMapping("/{id}")
    public void extendBorrowing(@RequestBody BorrowingEntity borrowing, @PathVariable long id) {
        service.extendBorrowing(id, borrowing.getDuration());
    }

    /**
     * Ends a borrowing by media ID
     *
     * @param mediaId media ID of the borrowing to delete
     */
    @DeleteMapping
    public void deleteBorrowing(@RequestParam int mediaId) {
        service.endBorrowing(mediaId);
    }
}