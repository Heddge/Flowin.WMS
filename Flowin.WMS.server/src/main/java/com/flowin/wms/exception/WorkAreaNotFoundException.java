package com.flowin.wms.exception;

public class WorkAreaNotFoundException extends RuntimeException {
    private final String exceptionMessage;
    private final String exceptionFinder;
    private final String exceptionLocation;
    public WorkAreaNotFoundException(String message, String _exFinder, String _exLocation)
    {
        super(message);
        exceptionMessage = message;
        exceptionFinder = _exFinder;
        exceptionLocation = _exLocation;
    }

    public String getExceptionMessage() {return exceptionMessage;}
    public String getExceptionFinder() {return exceptionFinder;}
    public String getExceptionLocation() {return exceptionLocation;}
}
