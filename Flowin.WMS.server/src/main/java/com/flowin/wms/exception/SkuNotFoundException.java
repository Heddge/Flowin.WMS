package com.flowin.wms.exception;

public class SkuNotFoundException extends RuntimeException {
    private final String message;
    private final String exceptionSku;
    private final String exceptedLocation;

    public SkuNotFoundException(String _message,
                                String _exceptionSku,
                                String _exceptedLocation) {
        super(_message);
        message = _message;
        exceptionSku = _exceptionSku;
        exceptedLocation = _exceptedLocation;
    }

    @Override
    public String getMessage() {return message;}
    public String getExceptionSku() {return exceptionSku;}
    public String getExceptedLocation() {return exceptedLocation;}
}
