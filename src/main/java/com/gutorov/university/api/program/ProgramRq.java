package com.gutorov.university.api.program;

import jakarta.validation.constraints.NotBlank;

public class ProgramRq {
    @NotBlank
    private String name;

    public ProgramRq() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}