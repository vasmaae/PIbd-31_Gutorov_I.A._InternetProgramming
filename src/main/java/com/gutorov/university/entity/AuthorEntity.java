package com.gutorov.university.entity;

public class AuthorEntity extends BaseEntity {
    private String name;

    public AuthorEntity() {
        super();
    }

    public AuthorEntity(String id, String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
