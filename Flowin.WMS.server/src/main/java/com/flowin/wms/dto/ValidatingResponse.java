package com.flowin.wms.dto;

public record ValidatingResponse(
        int code,
        String exceptionMessage,
        String exceptedArgument,
        String field
) {
}
