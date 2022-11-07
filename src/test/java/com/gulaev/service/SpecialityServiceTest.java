package com.gulaev.service;

import com.gulaev.dao.SpecialitiesAndTeachersDao;
import com.gulaev.dao.SpecialityDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.SpecialitiesAndTeachers;
import com.gulaev.models.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpecialityServiceTest {

    @Test
    void getSpecialityById() {
    }

    @Test
    void getAllTeacherBySpeciality() {
        SpecialityDao specialityDaoMock = mock(SpecialityDao.class);
        TeacherDao teacherDaoMock = mock(TeacherDao.class);
        SpecialitiesAndTeachersDao specialitiesAndTeachersDaoMock =
                mock(SpecialitiesAndTeachersDao.class);
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = new Teacher(1,"John", "Doe");
        Teacher teacher1 = new Teacher(2,"John", "Doe");
        Teacher teacher2 = new Teacher(2,"John", "Doe");
        teachers.add(teacher); teachers.add(teacher1); teachers.add(teacher2);

        SpecialitiesAndTeachers specialitiesAndTeachers = new SpecialitiesAndTeachers(1,2);
        SpecialitiesAndTeachers specialitiesAndTeachers1 = new SpecialitiesAndTeachers(1,3);
        List<SpecialitiesAndTeachers> specialitiesAndTeachersList = new ArrayList<>();
        specialitiesAndTeachersList.add(specialitiesAndTeachers);
        specialitiesAndTeachersList.add(specialitiesAndTeachers1);

        when(teacherDaoMock.loadAll()).thenReturn(teachers);
        when(specialitiesAndTeachersDaoMock.loadAll()).thenReturn(specialitiesAndTeachersList);

        List<Teacher> essepted = new ArrayList<>();
        essepted.add(teacher1); essepted.add(teacher2);

        SpecialityService specialityService =
                new SpecialityService(specialityDaoMock, specialitiesAndTeachersDaoMock, teacherDaoMock);

        assertEquals(essepted, specialityService.getAllTeacherBySpeciality(1));
    }
}