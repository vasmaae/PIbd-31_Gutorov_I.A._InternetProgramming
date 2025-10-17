package com.gutorov.university.api.author;

import java.util.UUID;

public class AuthorRs {
    public UUID id;
    public String name;

    public AuthorRs() {
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
