package com.flowin.wms.controller;

import com.flowin.wms.dto.ArgumentResponse;
import com.flowin.wms.dto.ValidatingResponse;
import com.flowin.wms.exception.InvalidArgumentException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidatingResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ValidatingResponse(400,
                        exception.getMessage(),
                        exception.getObjectName(),
                        exception.getFieldError() == null ? "" : exception.getFieldError().getField())
        );
    }
}
