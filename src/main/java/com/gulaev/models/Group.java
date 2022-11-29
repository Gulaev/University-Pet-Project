package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
public class Group implements Model {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupID;

    @Column(name = "course")
    private Integer course;

    public Group() {
    }

    public Group(Integer course) {
        this.course = course;
    }
}
