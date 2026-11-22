package com.flowin.wms.exception;

public class NeededEntityDisabledException extends RuntimeException {
    private final String exceptionMessage;
    private final String disabledEntity;
    private final String exceptionLocation;

    public NeededEntityDisabledException(String exMessage,
                                         String disEntity, String exLocation) {
        super(exMessage);
        exceptionMessage = exMessage;
        disabledEntity=disEntity;
        exceptionLocation=exLocation;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getDisabledEntity() {
        return disabledEntity;
    }

    public String getExceptionLocation() {
        return exceptionLocation;
    }
}
