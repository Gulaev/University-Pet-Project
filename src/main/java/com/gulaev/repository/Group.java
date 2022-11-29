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
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "groups")
public class Group implements Model {

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupID;

    @Column(name = "course")
    private Integer course;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "group_and_lesson",
        joinColumns = {@JoinColumn(name = "group_id")},
        inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
    private Set<Lesson> lessons = new HashSet<>();

    public Group() {
    }

    public Group(Integer course, Set<Lesson> lessons) {
        this.course = course;
        this.lessons = lessons;
    }
}
