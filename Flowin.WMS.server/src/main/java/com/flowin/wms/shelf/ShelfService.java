package com.flowin.wms.shelf;

import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.rack.RackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShelfService {
    private final ShelfRepository repository;
    private final RackService rackService;
    private final ShelfMapper mapper;

    public ShelfService(ShelfRepository repo, RackService _rackService, ShelfMapper shelfMap)
    {
        repository = repo;
        rackService = _rackService;
        mapper = shelfMap;
    }

    public List<ShelfDto.ShelfResponse> getShelves()
    {
        return repository.findAll().stream().map(mapper::toShelfResponse).toList();
    }

    @Transactional(readOnly = true)
    public ShelfDto.ShelfResponse getShelfById(Integer id)
    {
        return mapper.toShelfResponse(getShelfByIdEntity(id));
    }

    @Transactional(readOnly = true)
    public Shelf getShelfByIdEntity(Integer id)
    {
        if (id == null || id < 1)
            throw new InvalidArgumentException(
                    "ERROR: Argument id was null or less than 1.",
                    "ARGUMENT: id",
                    "PLACED: ShelfService::getShelfByIdEntity"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't shelf with id %d",id),
                    String.format("ENTITY: Shelf with id %d", id),
                    "PLACED: ShelfService::getShelfByIdEntity."
            );

        return response.get();
    }

    @Transactional
    public ShelfDto.ShelfResponse postShelf(ShelfDto.ShelfRequest request)
    {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Argument request was null.",
                    "ARGUMENT: Request",
                    "PLACED: ShelfService::postShelf"
            );

        var rackResponse = rackService.getRackByIdEntity(request.rackId());

        if (rackResponse == null)
            throw new EntityNotFoundException(
                    String.format("ERROR: Creation failure: There isn't rack with id %d", request.rackId()),
                    String.format("ENTITY: Rack with id %d", request.rackId()),
                    "PLACED: ShelfService::postShelf."
            );

        Shelf requesting = new Shelf(rackResponse, true);

        return mapper.toShelfResponse(repository.save(requesting));
    }

    // TODO: Add deactivation method (delete) that checking SKUs in bins in this shelf.
}
