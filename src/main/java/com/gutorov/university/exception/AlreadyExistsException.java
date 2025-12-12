package com.gutorov.university.exception;

import java.util.UUID;

public class AlreadyExistsException extends RuntimeException {
    public <T> AlreadyExistsException(Class<T> entClass, String name) {
        super(String.format("%s with name %s is already exists", entClass.getSimpleName(), name));
    }

    public <T> AlreadyExistsException(Class<T> entClass, UUID id1, UUID id2) {
        super(String.format("%s with id [%s, %s] is already exists", entClass.getSimpleName(), id1.toString(), id2.toString()));
    }
}
