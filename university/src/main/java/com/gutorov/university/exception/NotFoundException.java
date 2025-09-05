package com.gutorov.university.exception;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> entity, String id) {
        super(String.format("Entity \"%s\" with id \"%s\" not found", entity.getSimpleName(), id));
    }
}
