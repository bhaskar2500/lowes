package com.lowes.urlshortner.services.exception;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "Internal Server Error occured");

    private final int code;   // in kilograms
    private final String message; // in meters

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
