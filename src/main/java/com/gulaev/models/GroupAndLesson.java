package com.gulaev.models;

import lombok.Data;

@Data
public class GroupAndLesson implements Model{

    private Integer groupId;
    private Integer lessonId;

    public GroupAndLesson(Integer groupId, Integer lessonId) {
        this.groupId = groupId;
        this.lessonId = lessonId;
    }

    public GroupAndLesson() {
    }
}
