package com.gulaev.models;

import lombok.Data;

@Data
public class Student implements Model {

    private int studentId;
    private String firstName;
    private String lastName;
    private Integer groupId;

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName, Integer groupId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
    }

}
