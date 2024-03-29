package org.example.exception;

public class HardShutDownException extends RuntimeException {
    public HardShutDownException(String message) {
        super(message);
    }
}
