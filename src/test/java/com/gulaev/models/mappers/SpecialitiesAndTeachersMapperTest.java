package com.gulaev.models.mappers;

import com.gulaev.models.SpecialitiesAndTeachers;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecialitiesAndTeachersMapperTest {

    @Test
    void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("teacher_id")).thenReturn(1);
        when(resultSet.getInt("speciality_id")).thenReturn(1);

        SpecialitiesAndTeachers specialitiesAndTeachers = new SpecialitiesAndTeachers();
        specialitiesAndTeachers.setSpecialityId(1); specialitiesAndTeachers.setTeacherId(1);

        SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper = new SpecialitiesAndTeachersMapper();

        assertEquals(specialitiesAndTeachers, specialitiesAndTeachersMapper.mapRow(resultSet,1));


    }
}