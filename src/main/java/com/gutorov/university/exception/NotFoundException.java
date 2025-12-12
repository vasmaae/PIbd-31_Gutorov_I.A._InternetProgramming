package com.gutorov.university.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> entity, String id) {
        super(String.format("Entity \"%s\" with id \"%s\" not found", entity.getSimpleName(), id));
    }

    public <T> NotFoundException(Class<T> entClass, UUID id1, UUID id2) {
        super(String.format("%s with id [%s, %s] is not found", entClass.getSimpleName(), id1.toString(), id2.toString()));
    }
}
