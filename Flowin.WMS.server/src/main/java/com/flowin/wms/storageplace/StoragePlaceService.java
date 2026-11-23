package com.flowin.wms.storageplace;

import com.flowin.wms.exception.SavingFailException;
import com.flowin.wms.storageplace.StoragePlaceDto.*;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.workarea.WorkArea;
import com.flowin.wms.workarea.WorkAreaDto;
import com.flowin.wms.workarea.WorkAreaService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoragePlaceService {
    private final StoragePlaceRepository repository;
    private final StoragePlaceMapper mapper;
    private final WorkAreaService workAreaService;
    private final EntityManager entityManager;

    public StoragePlaceService(StoragePlaceRepository repo, EntityManager entMan,
                               StoragePlaceMapper map, WorkAreaService was)
    {
        repository = repo;
        mapper = map;
        workAreaService = was;
        entityManager = entMan;
    }

    public List<StoragePlaceResponse> getStoragePlaces()
    {
        return repository.findAll().stream().map(mapper::toStoragePlaceResponse).toList();
    }

    public StoragePlaceResponse getStoragePlaceById(Integer id)
    {
        return mapper.toStoragePlaceResponse(getStoragePlaceByIdEntity(id));
    }


    public boolean getIsActive(Integer id)
    {
        return repository.getIsActiveById(id);
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

    @Transactional
    public StoragePlaceResponse postStoragePlace(StoragePlaceRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Request was null",
                    "ARGUMENT: Request.",
                    "PLACED: StoragePlaceService::postStoragePlace."
            );

//        System.out.println("trying get new id");
//        var newId = repository.getNewId();
//        System.out.println("got new id");
//        entityManager.persist(new StoragePlace(request.storagePlaceInfo(), true));
        var requesting = repository.save(new StoragePlace(request.storagePlaceInfo(), true));
        System.out.println("saved StoragePlace");

//        EntityManager manage = EntityManager.create;

        if (request.needWorkArea())
        {
            System.out.println("needing workArea");
            var workAreaResponse = workAreaService.getWorkAreaByIdOptionalEntity(requesting.getId().intValue());
            System.out.println("checking extisting workArea");
            if (workAreaResponse.isEmpty())
            {
                System.out.println("workarea doesnt exist");
                var workAreaRequest = new WorkArea(requesting.getId(), request.storagePlaceInfo(), true);
                if (workAreaService.postWorkAreaFromEntity(workAreaRequest) == null) {
                    System.out.println("workarea saving failed");

                    throw new SavingFailException(
                            String.format("ERROR: Entity workArea for storage place with id %d saving failed", requesting.getId().intValue()),
                            String.format("ENTITY: workArea with id %d", requesting.getId().intValue()),
                            "PLACED: StoragePlaceService::postStoragePlace"
                    );

                }
                    System.out.println("workarea saved");
            }
        }
        System.out.println("nice!");

        return new StoragePlaceDto.StoragePlaceResponse(requesting.getId(),
                request.storagePlaceInfo(), true);
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
