package com.flowin.wms.exception;

public class SavingFailException extends RuntimeException {
    public final String message;
    public final String savingFailedForEntity;
    public final String exceptionLocation;

    public SavingFailException(String _message,
                               String _savingFailedForEntity,
                               String _exceptionLocation) {
        super(_message);
        message = _message;
        savingFailedForEntity = _savingFailedForEntity;
        exceptionLocation = _savingFailedForEntity;
    }

    @Override
    public String getMessage() {return message;}
    public String getSavingFailedForEntity() {return savingFailedForEntity;}
    public String getExceptionLocation() {return exceptionLocation;}
}
