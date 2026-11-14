package com.flowin.wms.dto;

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
            String argument
    ) {
    }
}