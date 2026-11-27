package com.flowin.wms.exception;

public class ExceptionResponses {
    public record CategoryResponse(
            int code,
            String message,
            String neededCategory
    ) {
    }

    public record ValidatingResponse(
            int code,
            String exceptionMessage,
            String exceptedArgument,
            String field
    ) {
    }

    public record ArgumentResponse(
            int code,
            String message,
            String argument,
            String location
    ) {
    }

    public record NotFoundResponse(
            int code,
            String message,
            String notFoundArgument
    ) {
    }

    public record SavingFailResponse(
            int code,
            String message,
            String failedArgument
    ) {
    }

    public record DeletingFailResponse(
            int code,
            String message,
            String failedArgument
    ) {
    }

    public record EntityNotFoundResponse(
            int code,
            String message,
            String failedArgument
    ) {
    }

    public record PresenceOfDependentEntitiesResponse(
            int code,
            String message,
            String failedEntity
    ){
    }

    public record NeededEntityDisabledResponse(
            int code,
            String message,
            String disabledEntity
    ){
    }

    public record NotEnoughSpaceResponse(
            int code,
            String message,
            String notEnoughIn
    ){
    }
}