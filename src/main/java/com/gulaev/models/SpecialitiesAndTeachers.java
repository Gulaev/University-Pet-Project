package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "specialities_and_teachers")
@AllArgsConstructor
public class SpecialitiesAndTeachers implements Model {

    @Id
    @Column(name = "speciality_id")
    private Integer specialityId;

    @Id
    @Column(name = "teacher_id")
    private Integer teacherId;

    public SpecialitiesAndTeachers() {
    }

}
