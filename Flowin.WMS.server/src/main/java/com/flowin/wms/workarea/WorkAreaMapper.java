package com.flowin.wms.workarea;

import com.flowin.wms.workarea.WorkAreaDto.*;
import org.springframework.stereotype.Component;

@Component
public class WorkAreaMapper {
    public WorkAreaResponse toWorkAreaResponse(WorkArea workArea)
    {
        return new WorkAreaResponse(workArea.getId(), workArea.getName(), workArea.getIsActive());
    }
}
