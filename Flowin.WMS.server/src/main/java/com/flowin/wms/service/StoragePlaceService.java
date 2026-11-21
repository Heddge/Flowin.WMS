package com.flowin.wms.service;

import com.flowin.wms.dto.StoragePlaceDto.*;
import com.flowin.wms.entity.StoragePlace;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.mapper.StoragePlaceMapper;
import com.flowin.wms.repository.StoragePlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoragePlaceService {
    private final StoragePlaceRepository repository;
    private final StoragePlaceMapper mapper;

    public StoragePlaceService(StoragePlaceRepository repo,
                               StoragePlaceMapper map)
    {
        repository = repo;
        mapper = map;
    }

    public List<StoragePlaceResponse> getStoragePlaces()
    {
        return repository.findAll().stream().map(mapper::toStoragePlaceResponse).toList();
    }

    public StoragePlaceResponse getStoragePlaceById(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Id was null or less than 1.",
                    "ARGUMENT: id.",
                    "PLACED: StoragePlaceService::getStoragePlaceById."
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't storage place with id %d", id),
                    String.format("ENTITY: Storage place with id %d", id),
                    "PLACED: StoragePlaceService::getStoragePlaceById."
            );

        return mapper.toStoragePlaceResponse(response.get());
    }


    public StoragePlace getStoragePlaceByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Id was null or less than 1.",
                    "ARGUMENT: id.",
                    "PLACED: StoragePlaceService::getStoragePlaceById."
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't storage place with id %d", id),
                    String.format("ENTITY: Storage place with id %d", id),
                    "PLACED: StoragePlaceService::getStoragePlaceById."
            );

        return response.get();
    }

    public StoragePlaceResponse postStoragePlace(StoragePlaceRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Request was null",
                    "ARGUMENT: Request.",
                    "PLACED: StoragePlaceService::postStoragePlace."
            );
        var requesting = new StoragePlace(request.storagePlaceInfo(), true);
        return mapper.toStoragePlaceResponse(repository.save(requesting));
    }

    public StoragePlaceResponse deactivateStoragePlace(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Id was null or less than 1.",
                    "ARGUMENT: id.",
                    "PLACED: StoragePlaceService::deactivateStoragePlace."
            );

        // TODO add checking linked Areas and Bins and SKUs in them.
        //  Also checking unfinished task with these storage places.

        StoragePlace required_place = getStoragePlaceByIdEntity(id);
        required_place.deactivate();

        return mapper.toStoragePlaceResponse(repository.save(required_place));
    }
}
