package com.flowin.wms.dto;

public record ArgumentResponse(
        int code,
        String message,
        String argument,
        String location
) {}
