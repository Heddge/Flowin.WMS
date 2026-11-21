package com.flowin.wms.controller;

import com.flowin.wms.dto.ExceptionResponses;
import com.flowin.wms.exception.*;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponses.NotFoundResponse(404,
                        exception.getMessage(),
                        exception.getExceptionSku())
        );
    }

    @ExceptionHandler(SavingFailException.class)
    public ResponseEntity<ExceptionResponses.SavingFailResponse> handleSavingFailException(
            SavingFailException exception
    )
    {
        return ResponseEntity.internalServerError().body(
                new ExceptionResponses.SavingFailResponse(409,
                        exception.getMessage(),
                        exception.getSavingFailedForEntity()
                )
        );
    }

    @ExceptionHandler(WorkAreaNotFoundException.class)
    public ResponseEntity<ExceptionResponses.NotFoundResponse> handleWorkAreaNotFoundException(
            WorkAreaNotFoundException exception)
    {
        return ResponseEntity.status(404).body(
                new ExceptionResponses.NotFoundResponse(
                        404, exception.getExceptionMessage(), exception.getExceptionFinder()
                )
        );
    }

    @ExceptionHandler(DeletingFailException.class)
    public ResponseEntity<ExceptionResponses.DeletingFailResponse> handleDeletingFailException(
            DeletingFailException exception
    )
    {
        return ResponseEntity.badRequest().body(
                new ExceptionResponses.DeletingFailResponse(
                        400,
                        exception.getMessage(),
                        exception.getDeletingFailedForEntity()
                )
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponses.EntityNotFoundResponse> handleEntityNotFoundException(
            EntityNotFoundException exception)
    {
        return ResponseEntity.status(404).body(
                new ExceptionResponses.EntityNotFoundResponse(
                        404,
                        exception.getExceptionMessage(),
                        exception.getExceptionEntity()
                )
        );
    }

    @ExceptionHandler(PresenceOfDependentEntitiesException.class)
    public ResponseEntity<ExceptionResponses.PresenceOfDependentEntitiesResponse> handlePresenceOfDependentException(
            PresenceOfDependentEntitiesException exception)
    {
        return ResponseEntity.status(424).body(
                new ExceptionResponses.PresenceOfDependentEntitiesResponse(
                        424,
                        exception.getExceptionMessage(),
                        exception.getExceptionEntity()
                )
        );
    }
}
