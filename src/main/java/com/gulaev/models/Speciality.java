package com.gulaev.models;

import lombok.Data;

@Data
public class Speciality implements Model {

    private Integer specialityId;
    private String nameOfSpeciality;
    private Integer course;
    private Integer audienceId;


    public Speciality(){
    }

    public Speciality(Integer specialityId, String nameOfSpeciality, Integer course, Integer audienceId) {
        this.specialityId = specialityId;
        this.nameOfSpeciality = nameOfSpeciality;
        this.course = course;
        this.audienceId = audienceId;
    }
}
