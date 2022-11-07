package com.gulaev.models;

import lombok.Data;

@Data
public class Teacher implements Model {

    private Integer teacherId;
    private String firstName;
    private String lastName;

    public Teacher() {
    }

    public Teacher(Integer teacherId, String firstName, String lastName) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
