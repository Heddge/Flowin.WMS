package com.flowin.wms.mapper;

import com.flowin.wms.dto.WorkAreaDto.*;
import com.flowin.wms.entity.WorkArea;
import org.springframework.stereotype.Component;

@Component
public class WorkAreaMapper {
    public WorkAreaResponse toWorkAreaResponse(WorkArea workArea)
    {
        return new WorkAreaResponse(workArea.getId(), workArea.getName());
    }
}
