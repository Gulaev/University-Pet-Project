package com.gulaev;

import com.gulaev.config.SpringConfig;
import com.gulaev.dao.*;
import com.gulaev.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;


public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  @Autowired private ApplicationContext context;

  public static void main(String[] args) {

    //        Assert.isInstanceOf(AbstractApplicationContext.class, SpringConfig.class);
    //        ((AnnotationConfigApplicationContext) context).refresh();
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    StudentDao studentDao = context.getBean(StudentDao.class);
    AudienceDao audienceDao = context.getBean(AudienceDao.class);
    TeacherDao teacherDao = context.getBean(TeacherDao.class);
    GroupDao groupDao = context.getBean(GroupDao.class);
    SpecialityDao specialityDao = context.getBean(SpecialityDao.class);
    SubjectDao subjectDao = context.getBean(SubjectDao.class);
    LessonDao lessonDao = context.getBean(LessonDao.class);
    SpecialitiesAndTeachersDao specialitiesAndTeachersDao =
        context.getBean(SpecialitiesAndTeachersDao.class);
    GroupAndLessonDao groupAndLessonDao = context.getBean(GroupAndLessonDao.class);

    Timestamp lessonStart = Timestamp.valueOf("2022-07-12 09:30:00.000");
    Timestamp lessonEnd = Timestamp.valueOf("2022-07-12 11:00:00.000");

    //        teacherDao.addNewTeacher("John", "Doe");
    //        audienceDao.addNewAudience(1, 1, 100, true, true);
    //        groupDao.addNewGroup(1);
    //        studentDao.addStudent("John", "Doe", 1);
    //        specialityDao.addNewSpeciality("Math",1,1);
    //        subjectDao.addNewSubject("Computer Science", "Java", 1, 1);
    //        lessonDao.addNewLesson("Math", lessonStart, lessonEnd, 1,1);
    //        specialitiesAndTeachersDao.addNewSpecialitiesAndTeachers(1,1);
    //        groupAndLessonDao.addNewGroupAndLesson(1,1);

    //        System.out.println(teacherDao.getById(1).toString());
    //        System.out.println(audienceDao.getById(1).toString());
    //        System.out.println(studentDao.getById(1).toString());
    //        System.out.println(groupDao.getById(1).toString());
    //        System.out.println(specialityDao.getById(1).toString());
    //        System.out.println(subjectDao.getById(1).toString());
    //        System.out.println(lessonDao.getById(1).toString());
    //        System.out.println(specialitiesAndTeachersDao.getById(1));
    //        System.out.println(groupAndLessonDao.getById(1).toString());

    LessonService lessonService = new LessonService(lessonDao, subjectDao, audienceDao);
    SubjectService subjectService = new SubjectService(subjectDao, specialityDao);
    GroupAndLessonService groupAndLessonService =
        new GroupAndLessonService(groupAndLessonDao, groupDao, lessonDao);
    StudentService studentService =
        new StudentService(
            studentDao, groupDao, groupAndLessonDao, lessonDao, groupAndLessonService);
    GroupService groupService = new GroupService(groupDao);
    SpecialityService specialityService =
        new SpecialityService(specialityDao, specialitiesAndTeachersDao, teacherDao);
    TeacherService teacherService = new TeacherService(teacherDao, subjectDao, lessonDao);
    AudienceService audienceService = new AudienceService(audienceDao);

//            lessonService.addNewLesson("Rom", lessonStart, lessonEnd, 1,"Computer Science");
    //        subjectService.addNewSubject("Rand", "Rand course", "Math",1);

     groupAndLessonService.getAllLessonsByOneGroup(10).forEach(System.out::println);

//     logger.info("massege {}", LocalDateTime.now());

            studentService.getStudentByGroups(3).forEach(System.out::println);
           studentService.getStudentByName("John").forEach(System.out::println);
    //        specialityService.getAllTeacherBySpeciality(3).forEach(System.out::println);
    teacherService.getTeacherLessonByTeacherId(45).forEach(System.out::println);
    //audienceService.updateAudienceById(1, 1, 11, 100, true, false);
//    teacherService.getTeacherById(121212);

    // Student studentdd= new Student(12, "Ron", "Howard", 1);

    // studentDao.update(1,studentdd);

  }
}
