package com.gulaev.domain;

import com.gulaev.dao.GroupAndLessonDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.StudentDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.Lesson;
import com.gulaev.service.GroupAndLessonService;
import com.gulaev.service.LessonService;
import com.gulaev.service.StudentService;
import com.gulaev.service.TeacherService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class Timetable {

    private LessonService lessonService;
    private TeacherService teacherService;
    private StudentService studentService;
    private GroupAndLessonService groupAndLessonService;
    private GroupAndLessonDao groupAndLessonDao;
    private LessonDao lessonDao;
    private TeacherDao teacherDao;
    private StudentDao studentDao;

//    @Autowired
//    public Timetable(LessonService lessonService, TeacherService teacherService, StudentService studentService,
//                     LessonDao lessonDao, TeacherDao teacherDao, StudentDao studentDao,GroupAndLessonService groupAndLessonService,
//                     GroupAndLessonDao groupAndLessonDao) {
//        this.lessonService = lessonService;
//        this.teacherService = teacherService;
//        this.studentService = studentService;
//        this.lessonDao = lessonDao;
//        this.teacherDao = teacherDao;
//        this.studentDao = studentDao;
//        this.groupAndLessonDao = groupAndLessonDao;
//        this.groupAndLessonService = groupAndLessonService;
//    }




}
