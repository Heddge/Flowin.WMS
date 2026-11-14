package com.flowin.wms.exception;

public class CategoryNotFoundException extends RuntimeException {
    private final String message;
    private final String neededCategory;
    private final String exceptionLocation;

    public CategoryNotFoundException(String _message,
                                     String _neededCategory,
                                     String _exceptionLocation) {
        super(_message);
        message = _message;
        neededCategory = _neededCategory;
        exceptionLocation = _exceptionLocation;
    }

    public String getMessage() {return message;}
    public String getNeededCategory() {return neededCategory;}
    public String getExceptionLocation() {return exceptionLocation;}
}
