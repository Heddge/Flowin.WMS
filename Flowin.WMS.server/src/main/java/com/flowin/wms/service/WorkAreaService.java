package com.flowin.wms.service;

import com.flowin.wms.dto.WorkAreaDto.*;
import com.flowin.wms.entity.WorkArea;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.WorkAreaNotFoundException;
import com.flowin.wms.mapper.WorkAreaMapper;
import com.flowin.wms.repository.WorkAreaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkAreaService {
    WorkAreaRepository repository;
    WorkAreaMapper mapper;

    public WorkAreaService(WorkAreaRepository _war, WorkAreaMapper _wam)
    {
        repository = _war;
        mapper = _wam;
    }

    public WorkAreaResponse getWorkAreaByName(String name)
    {
        if (name.isBlank())
            throw new InvalidArgumentException(
                    "Name was null or empty.",
                    String.format("name: %s", name),
                    "WorkAreaService::getWorkAreaByName"
            );

        var response = repository.findByWorkAreaName(name);

        if (response == null)
            throw new WorkAreaNotFoundException(
                    "There isn`t WorkArea with that name",
                    String.format("finder: %s", name),
                    "WorkAreaService::getWorkAreaByName"
            );

        return mapper.toWorkAreaResponse(response);
    }

    public WorkAreaResponse getWorkAreaById(Integer id)
    {
        if (id < 1)
            throw new InvalidArgumentException(
                    "Id was less than 1.",
                    String.format("id: %d", id),
                    "WorkAreaService::getWorkAreaById"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new WorkAreaNotFoundException(
                    "There isn`t WorkArea with that id",
                    String.format("finder: %d", id),
                    "WorkAreaService::getWorkAreaById"
            );

        return mapper.toWorkAreaResponse(response.get());
    }


    public WorkArea getWorkAreaByIdEntity(Integer id)
    {
        if (id < 1)
            throw new InvalidArgumentException(
                    "Id was less than 1.",
                    String.format("id: %d", id),
                    "WorkAreaService::getWorkAreaById"
            );

        var response = repository.findById(id);

        if (response.isEmpty())
            throw new WorkAreaNotFoundException(
                    "There isn`t WorkArea with that id",
                    String.format("finder: %d", id),
                    "WorkAreaService::getWorkAreaById"
            );

        return response.get();
    }

    private List<WorkArea> getWorkAreas()
    {
        return repository.findAll();
    }

    public List<WorkAreaResponse> getWorkAreasDto()
    {
        return getWorkAreas().stream().map(mapper::toWorkAreaResponse).toList();
    }
}
