package com.gulaev.service;

import com.gulaev.dao.AudienceDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.models.Lesson;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonServiceTest {

    @Test
    void getLessonById() {
    }

    @Test
    void getLessonByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp lessonStart = Timestamp.valueOf("2022-07-06 12:11:12.000");
        Timestamp lessonEnd = Timestamp.valueOf("2022-03-09 12:22:32.000");
        List<Lesson> lessons = new ArrayList<>();
        List<Lesson> truLesson = new ArrayList<>();

        Lesson lesson = new Lesson();
        lesson.setLessonId(1); lesson.setLessonName("Math"); lesson.setLessonStart(lessonStart);
        lesson.setLessonEnd(lessonEnd); lesson.setSubjectId(1); lesson.setAudienceId(1);
        Lesson lesson1 = new Lesson();
        lesson1.setLessonId(1); lesson1.setLessonName("Math"); lesson1.setLessonStart(lessonEnd);
        lesson1.setLessonEnd(lessonStart); lesson1.setSubjectId(1); lesson1.setAudienceId(1);
        lessons.add(lesson1);
        lessons.add(lesson);
        truLesson.add(lesson);

        LessonDao lessonDaoMock = mock(LessonDao.class);
        SubjectDao subjectDaoMock = mock(SubjectDao.class);
        AudienceDao audienceDaoMock = mock(AudienceDao.class);


        when(lessonDaoMock.loadAll()).thenReturn(lessons);

        LessonService lessonService = new LessonService(lessonDaoMock, subjectDaoMock, audienceDaoMock);

        System.out.println(lessonStart.getDate());
        assertEquals(truLesson, lessonService.getLessonByDate(lessonStart));

    }
}