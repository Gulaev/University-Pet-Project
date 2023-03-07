package com.gulaev.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Cascade;

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
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
    private Speciality speciality;

//    OneToMany
    @OneToMany(mappedBy = "subjectId", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL, targetEntity = Lesson.class)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Lesson> lessons;

    public Subject() {
    }

    public Subject(String subjectName, String subjectDescription, Teacher teacher,
        Speciality speciality, Set<Lesson> lessons) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
        this.teacher = teacher;
        this.speciality = speciality;
        this.lessons = lessons;
    }
}
