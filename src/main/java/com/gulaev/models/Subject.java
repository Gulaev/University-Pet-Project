package com.gulaev.models;

import lombok.Data;

@Data
public class Subject implements Model {

    private Integer subjectId;
    private String subjectName;
    private String subjectDescription;
    private Integer specialityId;
    private Integer subjectTeacherId;

    public Subject() {
    }

    public Subject(Integer subjectId, String subjectName, String subjectDescription, Integer specialityId, Integer subjectTeacherId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.specialityId = specialityId;
        this.subjectTeacherId = subjectTeacherId;
    }

}
