package com.gutorov.university.api.program;

import java.util.UUID;

public class ProgramRs {
    private UUID id;
    private String name;

    public ProgramRs() {
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