package com.gulaev.models.mappers;

import com.gulaev.dao.*;
import com.gulaev.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LessonMapperTest {

    @Test
    void mapRow() throws SQLException {
        Lesson lesson = new Lesson();

        ResultSet resultSet = mock(ResultSet.class);


        Timestamp lessonStart = Timestamp.valueOf("2022-07-12 09:30:00.000");
        Timestamp lessonEnd = Timestamp.valueOf("2022-07-12 11:00:00.000");


        when(resultSet.getInt("audience_id")).thenReturn(1);
        when(resultSet.getInt("subject_id")).thenReturn(1);
        when(resultSet.getInt("lesson_id")).thenReturn(1);
        when(resultSet.getTimestamp("lesson_start")).thenReturn(lessonStart);
        when(resultSet.getTimestamp("lesson_end")).thenReturn(lessonEnd);
        when(resultSet.getString("lesson_name")).thenReturn("Math");

        lesson.setLessonId(1);
        lesson.setLessonStart(lessonStart);
        lesson.setLessonEnd(lessonEnd);
        lesson.setAudienceId(1);
        lesson.setSubjectId(1);
        lesson.setLessonName("Math");

        LessonMapper lessonMapper = new LessonMapper();

        assertEquals(lesson, lessonMapper.mapRow(resultSet, 1));
    }
}