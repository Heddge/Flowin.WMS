package com.flowin.wms.exception;

public class InvalidArgumentException extends RuntimeException{
    private final String exceptionMessage;
    private final String invalidArgument;
    private final String exceptionLocation;

    public InvalidArgumentException(
            String _exceptionMessage, String _invalidArgument, String _exceptionLocation
    )
    {
        super(_exceptionMessage);
        this.exceptionMessage = _exceptionMessage;
        this.invalidArgument = _invalidArgument;
        this.exceptionLocation = _exceptionLocation;
    }

    public String getExceptionMessage() {return exceptionMessage;}
    public String getInvalidArgument() {return invalidArgument;}
    public String getExceptionLocation() {return exceptionLocation;}
}
