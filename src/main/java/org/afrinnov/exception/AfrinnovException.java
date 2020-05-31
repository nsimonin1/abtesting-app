package org.afrinnov.exception;

public class AfrinnovException extends RuntimeException {
    private final ErrorInfo errorInfo;

    private AfrinnovException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }

    private AfrinnovException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public static AfrinnovException makeInstance(ErrorInfo errorInfo) {
        return new AfrinnovException(errorInfo);
    }

    public static AfrinnovException makeInstance(String message, ErrorInfo errorInfo) {
        return new AfrinnovException(message, errorInfo);
    }

    public String getMessageInfo() {
        return errorInfo.getMessage();
    }

    public int getStatus() {
        return errorInfo.getCode();
    }
}
