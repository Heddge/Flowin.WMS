package com.flowin.wms.service;

import com.flowin.wms.dto.AreaDto.*;
import com.flowin.wms.entity.Area;
import com.flowin.wms.entity.StoragePlace;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.mapper.AreaMapper;
import com.flowin.wms.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaService {
    private final AreaRepository areaRepository;
    private final StoragePlaceRepository storagePlaceRepository;
    private final WarehouseRepository warehouseRepository;
    private final AreaTypeRepository areaTypeRepository;
    private final WorkAreaRepository workAreaRepository;
    private final AreaMapper mapper;

    public AreaService(AreaRepository areaRepo, WarehouseRepository whRepo,
                       AreaTypeRepository atrepo, WorkAreaRepository warepo,
                       StoragePlaceRepository storagePlaceRepo, AreaMapper map)
    {
        areaRepository = areaRepo;
        storagePlaceRepository = storagePlaceRepo;
        warehouseRepository = whRepo;
        areaTypeRepository = atrepo;
        workAreaRepository = warepo;
        mapper = map;
    }

    @Transactional(readOnly = true)
    public List<AreaResponse> getAreas()
    {
        return areaRepository.findAll().stream().map(mapper::toAreaResponse).toList();
    }

    @Transactional(readOnly = true)
    public AreaResponse getAreaById(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument was null or less than 1.",
                    "ARGUMENT: id",
                    "PLACED: AreaService::getAreaById."
            );

        var response = areaRepository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isnt area with id %d", id),
                    String.format("ENTITY: Area with id %d", id),
                    "PLACED: AreaService::getAreaById."
            );

        return mapper.toAreaResponse(response.get());
    }

    @Transactional
    public AreaResponse postArea(AreaRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Argument was null.",
                    "ARGUMENT: Request",
                    "AreaService::postArea"
            );

        var responseStoragePlace = storagePlaceRepository.findById(request.areaId());

        if (responseStoragePlace.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't storage place for area with id %d", request.areaId()),
                    String.format("ENTITY: Storage place with id %d", request.areaId()),
                    "PLACED: AreaService::postArea."
            );

        var responseWorkArea = workAreaRepository.findById(request.areaId());

        if (responseWorkArea.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't WorkArea for area with id %d", request.areaId()),
                    String.format("ENTITY: WorkArea with id %d", request.areaId()),
                    "PLACED: AreaService::postArea."
            );

        var responseWarehouse = warehouseRepository.findById(request.warehouseId());

        if (responseWarehouse.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't warehouse for area with id %d", request.warehouseId()),
                    String.format("ENTITY: Warehouse with id %d", request.warehouseId()),
                    "PLACED: AreaService::postArea."
            );

        var responseAreaType = areaTypeRepository.findByAreaTypeName(request.areaType());

        if (responseAreaType.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't area type for area with name %s", request.areaType()),
                    String.format("ENTITY: AreaType with name %s", request.areaType()),
                    "PLACED: AreaService::postArea."
            );

        Area requestionArea = new Area(request.areaId(), responseStoragePlace.get(),
                responseWorkArea.get(),responseWarehouse.get(), responseAreaType.get());

        return mapper.toAreaResponse(areaRepository.save(requestionArea));
    }
}
