package com.gulaev.models;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Lesson implements Model {

    private Integer lessonId;
    private String lessonName;
    private Timestamp lessonStart;
    private Timestamp lessonEnd;
    private Integer audienceId;
    private Integer subjectId;

    public Lesson() {
    }

    public Lesson(Integer lessonId, String lessonName, Timestamp lessonStart, Timestamp lessonEnd,
                  Integer audienceId, Integer subjectId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.lessonStart = lessonStart;
        this.lessonEnd = lessonEnd;
        this.audienceId = audienceId;
        this.subjectId = subjectId;
    }

}
