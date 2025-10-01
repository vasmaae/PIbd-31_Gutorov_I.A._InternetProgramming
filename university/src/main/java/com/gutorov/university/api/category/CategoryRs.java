package com.gutorov.university.api.category;

import java.util.UUID;

public class CategoryRs {
    public UUID id;
    public String name;

    public CategoryRs() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
