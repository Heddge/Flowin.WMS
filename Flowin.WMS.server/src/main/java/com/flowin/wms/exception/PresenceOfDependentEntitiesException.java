package com.flowin.wms.exception;

public class PresenceOfDependentEntitiesException extends RuntimeException {
    private final String exceptionMessage;
    private final String exceptionEntity;
    private final String exceptionLocation;

    public PresenceOfDependentEntitiesException(String _exceptionMessage,
                                                String _exceptionEntity,
                                                String _exceptionLocation) {
        super(_exceptionMessage);
        exceptionMessage = _exceptionMessage;
        exceptionEntity = _exceptionEntity;
        exceptionLocation = _exceptionLocation;
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
