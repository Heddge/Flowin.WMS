package com.flowin.wms.service;

import com.flowin.wms.dto.AreaTypeDto.*;
import com.flowin.wms.entity.AreaType;
import com.flowin.wms.exception.EntityNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.mapper.AreaTypeMapper;
import com.flowin.wms.repository.AreaTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AreaTypeService {
    private final AreaTypeRepository repository;
    private final AreaTypeMapper mapper;

    public AreaTypeService(AreaTypeRepository repo, AreaTypeMapper map) {
        repository = repo;
        mapper = map;
    }

    @Transactional(readOnly = true)
    public List<AreaTypeResponse> getAreaTypes() {
        List<AreaType> response = repository.findAll();

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    "ERROR: There aren`t any AreaTypes.",
                    "ENTITY: List<AreaType>",
                    "PLACED: AreaTypeService::getAreaTypes()"
            );

        return response.stream().map(mapper::toAreaTypeResponse).toList();
    }

    @Transactional(readOnly = true)
    public AreaTypeResponse getAreaTypeByName(String name) {
        return mapper.toAreaTypeResponse(getAreaTypeByNameEntity(name));
    }


    @Transactional(readOnly = true)
    public AreaType getAreaTypeByNameEntity(String name) {
        if (name.isBlank())
            throw new InvalidArgumentException(
                    "ERROR: Invalid argument name.",
                    "ARGUMENT: name",
                    "PLACED: AreaTypeService::getAreaTypeByName(name)"
            );

        var response = repository.findByAreaTypeName(name);

        if (response.isEmpty())
            throw new EntityNotFoundException(
                    "ERROR: Entity not found.",
                    String.format("ENTITY: AreaType with name %s", name),
                    "PLACED: AreaTypeService::getAreaTypeByName(name)"
            );

        return response.get();
    }

    @Transactional
    public AreaTypeResponse postAreaType(AreaTypeRequest request) {
        if (request == null)
            throw new InvalidArgumentException(
                    "ERROR: Invalid argument request.",
                    "ARGUMENT: request",
                    "PLACED: AreaTypeService::postAreaType(request)"
            );

        AreaType requestion = new AreaType(request.areaTypeName());

        return mapper.toAreaTypeResponse(repository.save(requestion));
    }
}
