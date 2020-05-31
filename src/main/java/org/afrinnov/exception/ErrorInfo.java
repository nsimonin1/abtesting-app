package org.afrinnov.exception;

public enum ErrorInfo {
    CAR_NOT_FOUND(101, "this car does not exist"),
    UPDATE_ENTITY_FAILED(901, "failed to update item"),
    PERSIST_ENTITY_FAILED(801, "failed to persist item"), TO_OLD_ITEM(201, "too old item"),
    DELETE_ENTITY_FAILED(903, "failed to delete item"),
    TECHNICAL_ISSUE(500, "technical error, see details");
    private final int code;
    private final String message;

    ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
