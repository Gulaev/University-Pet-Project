package com.gulaev.service;

import com.gulaev.dao.LessonDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.Lesson;
import com.gulaev.models.Subject;
import com.gulaev.models.Teacher;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    private TeacherDao teacherDaoMock = mock(TeacherDao.class);
    private SubjectDao subjectDaoMock = mock(SubjectDao.class);
    private LessonDao lessonDaoMock = mock(LessonDao.class);


    @Test
    void getTeacherById() {
        Teacher teacher = new Teacher(1,"John", "Doe");

        TeacherService teacherService = new TeacherService(teacherDaoMock, subjectDaoMock, lessonDaoMock);
        when(teacherDaoMock.getById(1)).thenReturn(teacher);
        assertEquals(teacherService.getTeacherById(1), teacher);
        verify(teacherDaoMock, times(1)).getById(1);
    }

    @Test
    void updateTeacherByTeacherId() {
        TeacherService teacherService = new TeacherService(teacherDaoMock, subjectDaoMock, lessonDaoMock);
        Teacher teacher = new Teacher(1, "John", "Doe");
        teacherService.updateTeacherByTeacherId(1,"John", "Doe");
        verify(teacherDaoMock, times(1)).update(1, teacher);
    }

    @Test
    void getTeacherLessonByTeacherId() {
        List<Lesson> lessons = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        List<Lesson> lessons1 = new ArrayList<>();
        Timestamp lessonStart = Timestamp.valueOf("2022-07-12 09:30:00.000");
        Timestamp lessonEnd = Timestamp.valueOf("2022-07-12 11:00:00.000");

        subjects.add(new Subject(1,"MATH", "Math Course", 1, 1));
        subjects.add(new Subject(1,"MATHHH", "Mathh Course", 1,2));

        lessons.add(new Lesson(1,"Math", lessonStart, lessonEnd, 1,1));
        lessons.add(new Lesson(1,"Math", lessonStart, lessonEnd,1,2));
        lessons1.add(new Lesson(1,"Math", lessonStart, lessonEnd, 1,1));

        when(teacherDaoMock.loadAll()).thenReturn(teachers);
        when(subjectDaoMock.loadAll()).thenReturn(subjects);
        when(lessonDaoMock.loadAll()).thenReturn(lessons);
        TeacherService teacherService = new TeacherService(teacherDaoMock, subjectDaoMock, lessonDaoMock);

        assertEquals(teacherService.getTeacherLessonByTeacherId(1), lessons1);

    }
}