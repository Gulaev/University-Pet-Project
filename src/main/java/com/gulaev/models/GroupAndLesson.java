package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Table;


@Entity
@Table(name = "group_and_lesson")
@Data
@AllArgsConstructor
public class GroupAndLesson implements Model{

    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

   // @OneToMany
    //@JoinColumn(name = "lesson_id", referencedColumnName = "lesson_id")

    @Id
    @Column(name = "lesson_id")
    private Integer lessonId;


    public GroupAndLesson() {
    }
}
