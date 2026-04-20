package com.baloise.m295.library.business.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.baloise.m295.library.common.MediaEntity;
import com.baloise.m295.library.persistence.repository.MediaRepository;
import com.baloise.m295.library.persistence.repository.BorrowingRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for handling media business logic
 * Responsible for retrieving, creating, updating and deleting media entries
 *
 * @author Julian Schiller
 */
@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository repo;
    private final BorrowingRepository borrowingRepo;

    /**
     * Retrieves all media entries from the database
     *
     * @return list of all media
     */
    public List<MediaEntity> getAllMedia(){
        return repo.findAll();
    }

    /**
     * Retrieves all media filtered by title
     *
     * @param title title filter
     * @return list of matching media entries
     */
    public List<MediaEntity> getAllFilteredByTitle(String title) {
        return repo.findByTitle(title);
    }

    /**
     * Retrieves a media entry by ID
     *
     * @param id media ID
     * @return media entity
     * @throws ResponseStatusException if media is not found
     */
    public MediaEntity getMediaById(long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));
    }

    /**
     * Creates a new media entry in the database
     *
     * @param media media entity to persist
     * @return the created MediaEntity
     */
    public MediaEntity createNewMedia(MediaEntity media) {
        return repo.save(media);
    }

    /**
     * Updates an existing media entry
     * Only non-null fields from the request are applied
     *
     * @param id media ID
     * @param updatedMedia incoming updated data
     * @throws ResponseStatusException if media is not found
     * @return the edited MediaEntity
     */
    public MediaEntity editMedia(long id, MediaEntity updatedMedia) {
        MediaEntity oldMedia = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));

        oldMedia = mergeMedia(oldMedia, updatedMedia);
        return repo.save(oldMedia);
    }

    /**
     * Deletes a media entry by ID
     *
     * @param id media ID
     * @throws ResponseStatusException if media is not found or when the media is borrowed
     */
    public void deleteMedia(long id) {
        MediaEntity media = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Media not found"));

        if (borrowingRepo.findByMedia_Id((int) id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Media is currently borrowed and cannot be deleted");
        }

        repo.delete(media);
    }

    /**
     * Merges updated media data into existing entity
     * Only non-null fields are overwritten
     *
     * @param oldEntity existing database entity
     * @param newEntity incoming update data
     * @return merged media entity
     */
    private MediaEntity mergeMedia(MediaEntity oldEntity, MediaEntity newEntity) {
        if (newEntity.getGenre() != null) {
            oldEntity.setGenre(newEntity.getGenre());
        }

        if (newEntity.getMinage() != null) {
            oldEntity.setMinage(newEntity.getMinage());
        }

        if (newEntity.getIsbn() != null) {
            oldEntity.setIsbn(newEntity.getIsbn());
        }

        if (newEntity.getLocationcode() != null) {
            oldEntity.setLocationcode(newEntity.getLocationcode());
        }

        return oldEntity;
    }
}
