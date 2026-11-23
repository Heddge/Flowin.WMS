package com.flowin.wms.rack;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.zone.ZoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RackService {
    private final RackRepository repository;
    private final ZoneService zoneService;
    private final RackMapper mapper;

    public RackService(RackRepository repo, RackMapper map, ZoneService _zoneService)
    {
        repository = repo;
        mapper = map;
        zoneService = _zoneService;
    }

    @Transactional(readOnly = true)
    public List<RackDto.RackResponse> getRacks()
    {
        return repository.findAll().stream().map(mapper::toRackResponse).toList();
    }

    @Transactional(readOnly = true)
    public RackDto.RackResponse getRackById(Integer id)
    {
        return mapper.toRackResponse(getRackByIdEntity(id));
    }

    @Transactional(readOnly = true)
    public Rack getRackByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument id was null or less than 1.",
                    "ARGUMENT: id",
                    "PLACED: RackService::getRackByIdEntity"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't rack with id %d",id),
                    String.format("ENTITY: Rack with id %d", id),
                    "PLACED: RackService::getRackByIdEntity."
            );

        return response.get();
    }

    @Transactional
    public RackDto.RackResponse postRack(RackDto.RackRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Request was null.",
                    "ARGUMENT: Request",
                    "PLACED: RackService::postRack"
            );

        var zoneResponse = zoneService.getZoneByIdEntity(request.zoneId());

        if (zoneResponse == null)
            throw new EntityNotFoundException(
                    String.format("ERROR: Can't create rack: There isn't zone with id %d", request.zoneId()),
                    String.format("ENTITY: Zone with id %d", request.zoneId()),
                    "PLACED: RackService::postRack."
            );

        Rack requesting = new Rack(zoneResponse, true);

        return mapper.toRackResponse(repository.save(requesting));
    }

    // TODO: Add deactivation method (delete) that checking SKUs in bins in this rack.
}
