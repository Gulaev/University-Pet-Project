package com.gulaev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Data
@Table(name = "lessons")
@AllArgsConstructor
public class Lesson implements Model {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "lesson_start")
    private Timestamp lessonStart;

    @Column(name = "lesson_end")
    private Timestamp lessonEnd;

    @Column(name = "audience_id")
    private Integer audienceId;

    @Column(name = "subject_id")
    private Integer subjectId;

    public Lesson() {
    }

}
