package com.gulaev.models.mappers;

import com.gulaev.models.GroupAndLesson;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GroupAndLessonMapperTest {

    @Test
    void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("lesson_id")).thenReturn(1);
        when(resultSet.getInt("group_id")).thenReturn(1);

        GroupAndLesson groupAndLesson = new GroupAndLesson();
        groupAndLesson.setGroupId(1); groupAndLesson.setLessonId(1);

        GroupAndLessonMapper groupAndLessonMapper = new GroupAndLessonMapper();

        assertEquals(groupAndLesson, groupAndLessonMapper.mapRow(resultSet, 1));
    }
}