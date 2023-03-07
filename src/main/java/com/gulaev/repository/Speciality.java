package com.gulaev.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Data
@Table(name = "specialities")
public class Speciality implements Model {

    @Id
    @Column(name = "speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialityId;

    @Column(name = "name_of_speciality")
    private String nameOfSpeciality;

    @Column(name = "course")
    private Integer course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audience_id", referencedColumnName = "audience_id")
    private Audience audience;


    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "specialities_and_teachers",
        joinColumns = {@JoinColumn(name = "speciality_id")}
        ,inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<Teacher> teachers;

//    OneToMany
    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY,
        targetEntity = Subject.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Subject> subjects;

    public Speciality(){
    }

    public Speciality(String nameOfSpeciality, Integer course, Audience audience,
        Set<Teacher> teachers,
        Set<Subject> subjects) {
        this.nameOfSpeciality = nameOfSpeciality;
        this.course = course;
        this.audience = audience;
        this.teachers = teachers;
        this.subjects = subjects;
    }
}
