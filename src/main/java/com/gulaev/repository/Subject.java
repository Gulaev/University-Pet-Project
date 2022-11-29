package com.gulaev.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name="subjects")
@AllArgsConstructor
public class Subject implements Model {

    @Id
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subjects_description")
    private String subjectDescription;

    @ManyToOne
    @JoinColumn(name = "subject_teacher_id", referencedColumnName = "teacher_id")
    private Teacher subjectTeacherId;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
    private Speciality specialityId;


    public Subject() {
    }

    public Subject(String subjectName, String subjectDescription, Speciality specialityId,
        Teacher subjectTeacherId) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.specialityId = specialityId;
        this.subjectTeacherId = subjectTeacherId;
    }
}
