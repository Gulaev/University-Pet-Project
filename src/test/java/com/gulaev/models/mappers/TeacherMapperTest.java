package com.gulaev.models.mappers;

import com.gulaev.models.Teacher;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherMapperTest {

    @Test
    void mapRow() throws SQLException {
        Teacher teacher = new Teacher();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("teacher_id")).thenReturn(1);
        when(resultSet.getString("first_name")).thenReturn("John");
        when(resultSet.getString("last_name")).thenReturn("Doe");

        teacher.setTeacherId(1);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        TeacherMapper teacherMapper = new TeacherMapper();
        assertEquals(teacher, teacherMapper.mapRow(resultSet,1));

    }
}