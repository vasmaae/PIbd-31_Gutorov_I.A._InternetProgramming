package com.gutorov.university.entity;

import java.util.UUID;

public abstract class BaseEntity {
    protected UUID id;

    protected BaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
