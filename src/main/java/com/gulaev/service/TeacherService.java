package com.gulaev.service;

import com.gulaev.dao.LessonDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.Lesson;
import com.gulaev.models.Subject;
import com.gulaev.models.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class TeacherService {

  private TeacherDao teacherDao;
  private SubjectDao subjectDao;
  private LessonDao lessonDao;

//  @Autowired
//  public TeacherService(TeacherDao teacherDao, SubjectDao subjectDao, LessonDao lessonDao) {
//    this.teacherDao = teacherDao;
//    this.subjectDao = subjectDao;
//    this.lessonDao = lessonDao;
//    log.trace("Initialization TeacherService");
//  }

  public Teacher getTeacherById(int id) {
    log.debug("getTeacher id = {}", id);
    Teacher teacher = teacherDao.getById(id);
    log.debug("returned teacher = {}", teacher);
    return teacher;
  }

  public void updateTeacherByTeacherId(int id, String firstName, String lastName) {
    log.debug("updateTeacherByTeacherId id = {}, firstName = {}, lastName = {}", id,firstName,lastName);
    Teacher teacher = new Teacher(id, firstName, lastName);
    teacherDao.update(id, teacher);
    log.debug("TeacherDao.update id = {} teacher = {}", id, teacher);
  }

  public List<Lesson> getTeacherLessonByTeacherId(int id) {
    log.debug("getTeacherLessonByTeacherId id = {}", id);
    List<Lesson> lessons = new ArrayList<>();
    List<Subject> subjects =
        subjectDao.loadAll().stream()
            .filter(subject -> subject.getSubjectTeacherId().equals(id))
            .collect(Collectors.toList());

    lessonDao.loadAll().stream()
        .forEach(
            lesson -> {
              subjects.stream()
                  .forEach(
                      subject -> {
                        if (lesson.getSubjectId().equals(subject.getSubjectId()))
                          lessons.add(lesson);
                      });
            });
    log.debug("returned lessons = {}", lessons);
    return lessons;
  }
}
