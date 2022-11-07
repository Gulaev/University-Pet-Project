package com.gulaev.models.mappers;

import com.gulaev.dao.AudienceDao;
import com.gulaev.dao.SpecialityDao;
import com.gulaev.models.Audience;
import com.gulaev.models.Speciality;
import com.gulaev.models.Subject;
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

class SubjectMapperTest {

    @Test
    void mapRow() throws SQLException {
        Subject subject = new Subject();
        ApplicationContext context = mock(ApplicationContext.class);
        ResultSet resultSet = mock(ResultSet.class);


        when(resultSet.getInt("speciality_id")).thenReturn(1);
        when(resultSet.getInt("subject_id")).thenReturn(1);
        when(resultSet.getString("subjects_description")).thenReturn("Math course");
        when(resultSet.getInt("subject_teacher_id")).thenReturn(1);

        subject.setSubjectId(1); subject.setSubjectDescription("Math course"); subject.setSubjectTeacherId(1);
        subject.setSpecialityId(1);

        SubjectMapper subjectMapper = new SubjectMapper();

        assertEquals(subject, subjectMapper.mapRow(resultSet,1));


    }
}