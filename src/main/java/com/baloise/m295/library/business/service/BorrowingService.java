package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.BorrowingEntity;
import com.baloise.m295.library.persistence.repository.BorrowingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BorrowingService {
    private final BorrowingRepository repo;

    public List<BorrowingEntity> getAllBorrowings() {
        return repo.findAll();
    }

    public void createBorrowing(BorrowingEntity borrowing) {
        repo.save(borrowing);
    }

    public void extendBorrowing(long id, short duration) {
        BorrowingEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing not found"));
        entity.setDuration(duration);
        repo.save(entity);
    }

    public void endBorrowing(int mediaId) {
        BorrowingEntity entity = repo.findByMedia_Id(mediaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrowing not found"));

        repo.delete(entity);
    }
}
