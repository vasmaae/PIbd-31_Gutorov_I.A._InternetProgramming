package com.gutorov.university.entity;

public class ProgramEntity extends BaseEntity {
    private String name;

    public ProgramEntity() {
        super();
    }

    public ProgramEntity(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProgramEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
