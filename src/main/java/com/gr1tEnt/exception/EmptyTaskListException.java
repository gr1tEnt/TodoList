package com.gr1tEnt.exception;

public class EmptyTaskListException extends RuntimeException {
    public EmptyTaskListException(String message) {
        super(message);
    }
}
