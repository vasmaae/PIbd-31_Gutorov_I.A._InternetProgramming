package com.gutorov.university.api.category;

import jakarta.validation.constraints.NotBlank;

public class CategoryRq {
    @NotBlank
    public String name;

    public CategoryRq() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
