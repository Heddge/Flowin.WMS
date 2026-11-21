package com.flowin.wms.service;

import com.flowin.wms.dto.WarehouseDto.*;
import com.flowin.wms.entity.Warehouse;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.mapper.WarehouseMapper;
import com.flowin.wms.repository.WarehouseRepository;
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
        var response = repository.findById(id);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    String.format("ERROR: There isn't warehouse for area with id %d", id),
                    String.format("ENTITY: Warehouse with id %d", id),
                    "PLACED: AreaService::postArea."
            );

        return mapper.toWarehouseResponse(response.get());
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
