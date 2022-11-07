package com.gulaev.models;

import lombok.Data;

@Data
public class Group implements Model {

    private Integer groupID;
    private Integer course;

    public Group() {
    }

    public Group(Integer groupID, Integer course) {
        this.groupID = groupID;
        this.course = course;
    }

}
