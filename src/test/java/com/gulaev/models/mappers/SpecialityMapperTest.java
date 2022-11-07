package com.gulaev.models.mappers;

import com.gulaev.dao.AudienceDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.Audience;
import com.gulaev.models.Speciality;
import com.gulaev.models.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecialityMapperTest {

    @Test
    void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        ApplicationContext context = mock(ApplicationContext.class);
        Speciality speciality = new Speciality();


        when(resultSet.getInt("speciality_id")).thenReturn(1);
        when(resultSet.getInt("course")).thenReturn(1);
        when(resultSet.getString("name_of_speciality")).thenReturn("Computer Science");
        when(resultSet.getInt("audience_id")).thenReturn(1);


        speciality.setSpecialityId(1);
        speciality.setNameOfSpeciality("Computer Science");
        speciality.setCourse(1);
        speciality.setAudienceId(1);

        SpecialityMapper specialityMapper = new SpecialityMapper();

        assertEquals(speciality, specialityMapper.mapRow(resultSet, 1));
    }
}