package com.flowin.wms.exception;

public class DeletingFailException extends RuntimeException {
    public final String message;
    public final String deletingFailedForEntity;
    public final String exceptionLocation;

    public DeletingFailException(String _message,
                               String _deletingFailedForEntity,
                               String _exceptionLocation) {
        super(_message);
        message = _message;
        deletingFailedForEntity = _deletingFailedForEntity;
        exceptionLocation = _exceptionLocation;
    }

    @Override
    public String getMessage() {return message;}
    public String getDeletingFailedForEntity() {return deletingFailedForEntity;}
    public String getExceptionLocation() {return exceptionLocation;}
}
