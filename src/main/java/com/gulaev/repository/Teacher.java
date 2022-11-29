package com.gulaev.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Entity
@Data
@Table(name = "teachers")
public class Teacher implements Model {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "teachers")
    private Set<Speciality> specialities = new HashSet<>();


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, Set<Speciality> specialities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialities = specialities;
    }
}
