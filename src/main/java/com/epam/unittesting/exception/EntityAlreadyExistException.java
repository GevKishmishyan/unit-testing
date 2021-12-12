package com.epam.unittesting.exception;

public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException() {
        super("Entity already exist exception.");
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }

}
