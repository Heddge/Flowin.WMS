package com.flowin.wms.warehouse;

import com.flowin.wms.warehouse.WarehouseDto.*;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseService {
    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    public WarehouseService(WarehouseRepository repo, WarehouseMapper map)
    {
        repository = repo;
        mapper = map;
    }

    public List<WarehouseResponse> getWarehouses()
    {
        return repository.findAll().stream().map(mapper::toWarehouseResponse).toList();
    }

    public WarehouseResponse getWarehouseByName(String name)
    {
        if (name.isBlank())
            throw new InvalidArgumentException(
                    "ERROR: Argument name was blank.",
                    "ARGUMENT: name",
                    "PLACED: WarehouseService::getWarehouseByName."
            );

        var response = repository.findByWhName(name);

        if (response == null)
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn`t entity with name %s", name),
                    String.format("ENTITY: Warehouse with name %s", name),
                    "PLACED: WarehouseService::getWarehouseByName."
            );

        return mapper.toWarehouseResponse(response);
    }

    public WarehouseResponse getWarehouseById(Integer id)
    {
        return mapper.toWarehouseResponse(getWarehouseByIdEntity(id));
    }


    public Warehouse getWarehouseByIdEntity(Integer id)
    {
        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't warehouse for area with id %d", id),
                    String.format("ENTITY: Warehouse with id %d", id),
                    "PLACED: AreaService::postArea."
            );

        return response.get();
    }

}
