package com.flowin.wms.controller;

import com.flowin.wms.dto.ArgumentResponse;
import com.flowin.wms.exception.InvalidArgumentException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ArgumentResponse> handleInvalidArgumentException(
            InvalidArgumentException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ArgumentResponse(400,
                        exception.getExceptionMessage(),
                        exception.getInvalidArgument(),
                        exception.getExceptionLocation()));
    }
}
