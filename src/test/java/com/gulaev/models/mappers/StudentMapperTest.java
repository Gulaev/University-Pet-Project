package com.gulaev.models.mappers;

import com.gulaev.models.Student;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentMapperTest {

    @Test
    void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Student student = new Student();

        when(resultSet.getInt("student_id")).thenReturn(1);
        when(resultSet.getString("first_name")).thenReturn("John");
        when(resultSet.getString("last_name")).thenReturn("Doe");
        when(resultSet.getInt("group_id")).thenReturn(1);

        student.setStudentId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setGroupId(1);

        StudentMapper studentMapper = new StudentMapper();

        assertEquals(student, studentMapper.mapRow(resultSet,1));

    }
}