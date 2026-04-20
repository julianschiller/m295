package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.BorrowingEntity;
import com.baloise.m295.library.persistence.repository.BorrowingRepository;
import com.baloise.m295.library.persistence.repository.CustomerRepository;
import com.baloise.m295.library.persistence.repository.MediaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for handling borrowing business logic
 * Responsible for creating, updating, and deleting borrowings as well as
 * validating business rules
 *
 * @author Julian Schiller
 */
@RequiredArgsConstructor
@Service
public class BorrowingService {

    private final BorrowingRepository repo;
    private final CustomerRepository customerRepo;
    private final MediaRepository mediaRepo;

    /**
     * Retrieves all borrowings from the database.
     *
     * @return list of all borrowings
     */
    public List<BorrowingEntity> getAllBorrowings() {
        return repo.findAll();
    }

    /**
     * Creates a new borrowing after validating:
     * - customer exists
     * - media exists
     * - media is not already borrowed
     *
     * @param borrowing borrowing entity to create
     * @return the saved BorrowingEntity
     */
    public BorrowingEntity createBorrowing(BorrowingEntity borrowing) {
        checkIfUserExists(borrowing.getCustomer().getId());
        checkIfMediaExists(borrowing.getMedia().getId());
        checkIfMediaIsAvailable(borrowing.getMedia().getId().intValue());

        return repo.save(borrowing);
    }

    /**
     * Extends the duration of an existing borrowing.
     *
     * @param id       borrowing ID
     * @param duration new duration in days
     * @return the edited Entity
     * @throws ResponseStatusException if borrowing is not found
     */
    public BorrowingEntity extendBorrowing(long id, short duration) {
        BorrowingEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing not found"));

        entity.setDuration(duration);
        return repo.save(entity);
    }

    /**
     * Ends a borrowing by media ID (returns the media).
     *
     * @param mediaId ID of the media to return
     * @throws ResponseStatusException if borrowing is not found
     */
    public void endBorrowing(int mediaId) {
        BorrowingEntity entity = repo.findByMedia_Id(mediaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing not found"));

        repo.delete(entity);
    }

    /**
     * Checks if a customer exists in the database.
     *
     * @param customerId ID of the customer
     * @throws ResponseStatusException if customer does not exist
     */
    private void checkIfUserExists(Long customerId) {
        if (!customerRepo.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }

    /**
     * Checks if a media item exists in the database.
     *
     * @param mediaId ID of the media
     * @throws ResponseStatusException if media does not exist
     */
    private void checkIfMediaExists(Long mediaId) {
        if (!mediaRepo.existsById(mediaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found");
        }
    }

    /**
     * Checks whether a media item is already borrowed.
     *
     * @param mediaId ID of the media
     * @throws ResponseStatusException if media is already borrowed
     */
    private void checkIfMediaIsAvailable(int mediaId) {
        if (repo.findByMedia_Id(mediaId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Media is already borrowed");
        }
    }
}