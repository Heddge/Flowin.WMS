package com.flowin.wms.exception;

public class NotEnoughSpaceException extends RuntimeException {
    private final String exceptionMessage;
    private final String notEnoughIn;
    private final String exceptionLocation;

    public NotEnoughSpaceException(String _exceptionMessage,
                                   String _notEnoughIn, String _exceptionLocation)
    {
        super(_exceptionMessage);
        exceptionMessage = _exceptionMessage;
        notEnoughIn = _notEnoughIn;
        exceptionLocation = _exceptionLocation;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getNotEnoughIn() {
        return notEnoughIn;
    }

    public String getExceptionLocation() {
        return exceptionLocation;
    }
}
