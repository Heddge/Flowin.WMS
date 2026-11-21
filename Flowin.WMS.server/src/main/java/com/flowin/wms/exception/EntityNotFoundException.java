package com.flowin.wms.exception;

public class EntityNotFoundException extends RuntimeException {
    private final String exceptionMessage;
    private final String exceptionEntity;
    private final String exceptionLocation;

    public EntityNotFoundException(String exMessage,
                                   String exEntity,
                                   String exLocation
    ) {
        super(exMessage);
        exceptionMessage = exMessage;
        exceptionEntity=exEntity;
        exceptionLocation=exLocation;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getExceptionEntity() {
        return exceptionEntity;
    }

    public String getExceptionLocation() {
        return exceptionLocation;
    }
}
