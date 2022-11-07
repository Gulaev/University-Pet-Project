package com.gulaev.domain;

import com.gulaev.dao.GroupAndLessonDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.StudentDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.service.GroupAndLessonService;
import com.gulaev.service.LessonService;
import com.gulaev.service.StudentService;
import com.gulaev.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Timetable {

    private static final Logger logger = LoggerFactory.getLogger(Timetable.class);
    private LessonService lessonService;
    private TeacherService teacherService;
    private StudentService studentService;
    private GroupAndLessonService groupAndLessonService;
    private GroupAndLessonDao groupAndLessonDao;
    private LessonDao lessonDao;
    private TeacherDao teacherDao;
    private StudentDao studentDao;

    @Autowired
    public Timetable(LessonService lessonService, TeacherService teacherService, StudentService studentService,
                     LessonDao lessonDao, TeacherDao teacherDao, StudentDao studentDao,GroupAndLessonService groupAndLessonService,
                     GroupAndLessonDao groupAndLessonDao) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.lessonDao = lessonDao;
        this.teacherDao = teacherDao;
        this.studentDao = studentDao;
        this.groupAndLessonDao = groupAndLessonDao;
        this.groupAndLessonService = groupAndLessonService;
    }



}
