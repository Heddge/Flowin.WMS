package com.flowin.wms.location;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.storageplace.StoragePlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;
    private final StoragePlaceService storagePlaceService;

    public LocationService(LocationRepository repo, LocationMapper map,
                           StoragePlaceService spService)
    {
        repository = repo;
        mapper = map;
        storagePlaceService = spService;
    }

    public List<LocationDto.LocationResponse> getLocations()
    {
        return repository.findAll().stream().map(mapper::toLocationResponse).toList();
    }

    public Location getLocationByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR. Id was null or less than 1.",
                    "ARGUMENT: Id",
                    "PLACED: LocationService::getLocationByIdEntity"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: Location with id %d not found", id),
                    String.format("ENTITY: Location with id %d", id),
                    "PLACED: LocationService::getLocationByIdEntity"
            );

        return response.get();
    }

    public LocationDto.LocationResponse getLocationById(Integer id)
    {
        return mapper.toLocationResponse(getLocationByIdEntity(id));
    }

    public LocationDto.LocationResponse postLocation(LocationDto.LocationRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR. Request was null.",
                    "ARGUMENT: Request",
                    "PLACED: LocationService::postLocation"
            );

        var storagePlace = storagePlaceService.getStoragePlaceByIdEntity(request.storagePlaceId());

        // TODO add checking address format.
        Location requesting = new Location(storagePlace, request.address());

        return mapper.toLocationResponse(repository.save(requesting));
    }
}
