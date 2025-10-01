package com.gutorov.university.api.author;

import jakarta.validation.constraints.NotBlank;

public class AuthorRq {
    @NotBlank
    public String name;

    public AuthorRq() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
