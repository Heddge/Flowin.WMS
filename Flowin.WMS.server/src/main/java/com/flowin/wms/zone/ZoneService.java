package com.flowin.wms.zone;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.NeededEntityDisabledException;
import com.flowin.wms.exception.PresenceOfDependentEntitiesException;
import com.flowin.wms.skucategory.SkuCategory;
import com.flowin.wms.skucategory.SkuCategoryService;
import com.flowin.wms.warehouse.Warehouse;
import com.flowin.wms.warehouse.WarehouseService;
import com.flowin.wms.workarea.WorkArea;
import com.flowin.wms.workarea.WorkAreaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneService {
    private final ZoneRepository repository;
    private final ZoneMapper mapper;
    private final WorkAreaService workAreaService;
    private final SkuCategoryService skuCategoryService;
    private final WarehouseService warehouseService;

    public ZoneService(ZoneRepository repo, ZoneMapper map, WorkAreaService was,
                       SkuCategoryService scs, WarehouseService ws)
    {
        repository = repo;
        mapper = map;
        workAreaService = was;
        skuCategoryService = scs;
        warehouseService = ws;
    }

    @Transactional(readOnly = true)
    public List<ZoneDto.ZoneResponse> getZones()
    {
        return repository.findAll().stream().map(mapper::toZoneResponse).toList();
    }

    @Transactional(readOnly = true)
    public Zone getZoneByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Id was null or less than 1.",
                    "ARGUMENT: Id.",
                    "PLACED: ZoneService::getZoneByIdEntity."
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: Entity with id %d not found", id),
                    String.format("ENTITY: Zone with id %d", id),
                    "PLACED: ZoneService::getZoneByIdEntity."
            );

        return response.get();
    }

    @Transactional(readOnly = true)
    public ZoneDto.ZoneResponse getZoneById(Integer id)
    {
        return mapper.toZoneResponse(getZoneByIdEntity(id));
    }

    @Transactional
    public ZoneDto.ZoneResponse postZone(ZoneDto.ZoneRequest zoneRequest)
    {
        if (zoneRequest == null)
            throw new InvalidArgumentException(
                    "ERROR: Request was null or invalid.",
                    "ARGUMENT: Requested Zone.",
                    "PLACED: ZoneService::postZone."
            );

        WorkArea workAreaResponse = workAreaService.getWorkAreaByIdEntity(zoneRequest.workAreaId());
        if (workAreaService.getCountForeignersById(workAreaResponse.getId().intValue()) != 0)
            throw new PresenceOfDependentEntitiesException(
                    String.format("ERROR: Existing related entities from WorkArea with id %d", zoneRequest.workAreaId()),
                    String.format("ENTITY: WorkArea with id %d has related entities from it", zoneRequest.workAreaId()),
                    "PLACED: ZoneService::postZone."
            );

        if (!workAreaResponse.getIsActive())
            throw new NeededEntityDisabledException(
                    String.format("ERROR: WorkArea with id %d is deactivated.", zoneRequest.workAreaId()),
                    String.format("ENTITY: WorkArea with id %d",zoneRequest.workAreaId()),
                    "PLACED: ZoneService::postZone"
            );

        SkuCategory skuCategoryResponse = skuCategoryService.getCategoryEntityByName(zoneRequest.skuCategoryName());
        Warehouse warehouseResponse = warehouseService.getWarehouseByIdEntity(zoneRequest.whId());

        Zone request = new Zone(workAreaResponse, skuCategoryResponse,
                warehouseResponse);

        return mapper.toZoneResponse(repository.save(request));
    }
}
