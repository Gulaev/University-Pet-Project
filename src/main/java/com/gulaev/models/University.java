package com.gulaev.models;

import java.util.List;
import lombok.Data;

@Data
public class University implements Model {


    private List<Audience> allAudience;
    private List<Student> allStudents;
    private List<Speciality> allSpeciality;
    private List<Group> allGroup;
    private List<Lesson> allLesson;
    private List<Teacher> allTeacher;


    public University() {
    }

    public University(List<Audience> allAudience, List<Student> allStudents,
        List<Speciality> allSpeciality, List<Group> allGroup, List<Lesson> allLesson,
        List<Teacher> allTeacher) {
        this.allAudience = allAudience;
        this.allStudents = allStudents;
        this.allSpeciality = allSpeciality;
        this.allGroup = allGroup;
        this.allLesson = allLesson;
        this.allTeacher = allTeacher;
    }
}
