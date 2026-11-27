package com.flowin.wms.area;

import com.flowin.wms.area.AreaDto.*;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.PresenceOfDependentEntitiesException;
import com.flowin.wms.areatype.AreaTypeService;
import com.flowin.wms.storageplace.StoragePlaceService;
import com.flowin.wms.workarea.WorkAreaService;
import com.flowin.wms.warehouse.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaService {
    private final AreaRepository areaRepository;
    private final StoragePlaceService storagePlaceService;
    private final WarehouseService warehouseService;
    private final AreaTypeService areaTypeService;
    private final WorkAreaService workAreaService;
    private final AreaMapper mapper;

    public AreaService(AreaRepository areaRepo, WarehouseService whService,
                       AreaTypeService atservice, WorkAreaService waservice,
                       StoragePlaceService storagePlaceSer, AreaMapper map)
    {
        areaRepository = areaRepo;
        storagePlaceService = storagePlaceSer;
        warehouseService = whService;
        areaTypeService = atservice;
        workAreaService = waservice;
        mapper = map;
    }

    @Transactional(readOnly = true)
    public List<AreaResponse> getAreas()
    {
        return areaRepository.findAll().stream().map(mapper::toAreaResponse).toList();
    }


    @Transactional(readOnly = true)
    public Boolean isAreaExists(Integer areaId)
    {
        if (areaId == null || areaId < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument was null or less than 1.",
                    "ARGUMENT: id",
                    "PLACED: AreaService::isAreaExists."
            );

        if (!areaRepository.existsByAreaId(areaId))
            throw new EntityNotFoundException(
                    String.format("ERROR: Area with id %d not found", areaId),
                    String.format("ENTITY: Area with id %d", areaId),
                    "PLACED: AreaService::isAreaExists"
            );

        return true;
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

        var storage_place = storagePlaceService.getStoragePlaceByIdEntity(request.areaId());
        if (!storage_place.getIsActive())
            throw new PresenceOfDependentEntitiesException(
                    "ERROR: Can't add area with that storage place because it's deactivated.",
                    String.format("ENTITY: Storage place with id %d is deactivated.", request.areaId()),
                    "PLACED: AreaService::postArea"
            );

        var work_area = workAreaService.getWorkAreaByIdEntity(request.areaId());
        if (!work_area.getIsActive())
            throw new PresenceOfDependentEntitiesException(
                    "ERROR: Can't add area with that work area because it's deactivated.",
                    String.format("ENTITY: Work area with id %d is deactivated.", request.areaId()),
                    "PLACED: AreaService::postArea"
            );

        Area requestionArea = new Area(storage_place, work_area,
                warehouseService.getWarehouseByIdEntity(request.warehouseId()),
                areaTypeService.getAreaTypeByNameEntity(request.areaType()));

        return mapper.toAreaResponse(areaRepository.save(requestionArea));
    }

    // TODO Deactivation func checking SKUs in it before disabling. Disabling in storage_places.is_active.
}
