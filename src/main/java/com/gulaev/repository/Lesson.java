package com.gulaev.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Data
@Table(name = "lessons")
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

    @ManyToOne
    @JoinColumn(name = "audience_id", referencedColumnName = "audience_id")
    private Audience audienceId;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subjectId;

    @ManyToMany(mappedBy = "lessons")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Group> groups;

    public Lesson() {
    }

    public Lesson(String lessonName, Timestamp lessonStart, Timestamp lessonEnd,
        Audience audienceId,
        Subject subjectId, Set<Group> groups) {
        this.lessonName = lessonName;
        this.lessonStart = lessonStart;
        this.lessonEnd = lessonEnd;
        this.audienceId = audienceId;
        this.subjectId = subjectId;
        this.groups = groups;
    }
}
