package com.gulaev.models;

import lombok.Data;

@Data
public class SpecialitiesAndTeachers implements Model {

    private Integer specialityId;
    private Integer teacherId;

    public SpecialitiesAndTeachers() {
    }

    public SpecialitiesAndTeachers(Integer specialityId, Integer teacherId) {
        this.specialityId = specialityId;
        this.teacherId = teacherId;
    }

}
