package com.flowin.wms.controller;

import com.flowin.wms.dto.ExceptionResponses;
import com.flowin.wms.exception.CategoryNotFoundException;
import com.flowin.wms.exception.InvalidArgumentException;
import com.flowin.wms.exception.SkuNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionResponses.ArgumentResponse> handleInvalidArgumentException(
            InvalidArgumentException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ExceptionResponses.ArgumentResponse(400,
                        exception.getExceptionMessage(),
                        exception.getInvalidArgument(),
                        exception.getExceptionLocation()));
    }

    // for bean-validating exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponses.ValidatingResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ExceptionResponses.ValidatingResponse(400,
                        exception.getMessage(),
                        exception.getObjectName(),
                        exception.getFieldError() == null ? "" : exception.getFieldError().getField())
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponses.CategoryResponse> handleCategoryNotFoundException(
            CategoryNotFoundException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ExceptionResponses.CategoryResponse(404,
                        exception.getMessage(),
                        exception.getNeededCategory())
        );
    }

    @ExceptionHandler(SkuNotFoundException.class)
    public ResponseEntity<ExceptionResponses.NotFoundResponse> handleSkuNotFoundException(
            SkuNotFoundException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ExceptionResponses.NotFoundResponse(404,
                        exception.getMessage(),
                        exception.getExceptionSku())
        );
    }
}
