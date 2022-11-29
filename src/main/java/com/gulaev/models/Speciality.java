package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "specialities")
@AllArgsConstructor
public class Speciality implements Model {

    @Id
    @Column(name = "speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialityId;

    @Column(name = "name_of_speciality")
    private String nameOfSpeciality;

    @Column(name = "course")
    private Integer course;

    @Column(name = "audience_id")
    private Integer audienceId;

//
//    @ManyToOne
//    @JoinColumn(name = "audience_id", referencedColumnName = "audience_id")
//    private Audience audienceId;


    public Speciality(){
    }
}
