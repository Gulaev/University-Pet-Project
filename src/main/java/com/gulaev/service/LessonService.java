package com.gulaev.service;

import com.gulaev.dao.AudienceDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.models.Audience;
import com.gulaev.models.Lesson;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class LessonService {

  private LessonDao lessonDao;
  private SubjectDao subjectDao;
  private AudienceDao audienceDao;

//  @Autowired
//  public LessonService(LessonDao lessonDao, SubjectDao subjectDao, AudienceDao audienceDao) {
//    this.lessonDao = lessonDao;
//    this.audienceDao = audienceDao;
//    this.subjectDao = subjectDao;
//    log.trace("Initialization LessonService");
//  }

  public Lesson getLessonById(int id) {
    log.debug("getLessonById id = {}", id);
    Lesson lesson = lessonDao.getById(id);
    log.debug("returned lesson = {}", lesson);
    return lesson;
  }

  public int geAudienceNumberById(int id) {
    log.debug("getLessonAudienceById");
    Audience byId = audienceDao.getById(id);
    log.debug("returned audience number = {}", byId.getAudienceNumber());
    return byId.getAudienceNumber();
  }

  public List<Lesson> getAllLessons() {
    log.debug("getAllLessons()");
    List<Lesson> lessons = lessonDao.loadAll();
    log.debug("Load All Lessons = {}", lessons);
    return lessons;
  }

  public List<Lesson> getLessonByDate(Timestamp timestamp) {
    log.debug("getLessonByDate date = {}", timestamp);
     List<Lesson> lessons = lessonDao.loadAll().stream()
        .filter(lesson -> lesson.getLessonStart().getDate() == timestamp.getDate())
        .collect(Collectors.toList());
     log.debug("returned lessons = {}", lessons);
     return lessons;
  }

  public List<Lesson> getLessonByName(String lessonName) {
    log.debug("getLessonByName lessonName = {}", lessonName);
    List<Lesson> lessons = lessonDao.loadAll().stream()
        .filter(lesson -> lesson.getLessonName().equals(lessonName))
        .collect(Collectors.toList());
    log.debug("returned lessons = {}", lessons);
    return lessons;
  }

  public void addNewLesson(
      String lessonName,
      Timestamp lessonStart,
      Timestamp lessonEnd,
      Integer audienceNumber,
      String subjectName) {
    log.debug(
        "addNewLesson lessonName = {} lessonStart = {} lessonEnd = {} audienceNumber = {} subjectName = {}",
        lessonName,
        lessonStart,
        lessonEnd,
        audienceNumber,
        subjectName);
    Integer subjectIdByName =
        subjectDao.loadAll().stream()
            .filter(subject -> subject.getSubjectName().equals(subjectName))
            .findAny()
            .orElse(null)
            .getSubjectId();
    lessonDao.addNewLesson(lessonName, lessonStart, lessonEnd, audienceNumber, subjectIdByName);
    log.debug(
        "LessonDao.addNewLesson lessonName ={}, lessonStart = {}, lessonEnd = {}, audienceNumber = {}, subjectIdByName = {}",
        lessonName,
        lessonStart,
        lessonEnd,
        audienceNumber,
        subjectIdByName);
  }

  public void updateLessonByLessonId(
      int id,
      String lessonName,
      Timestamp lessonStart,
      Timestamp lessonEnd,
      int audienceId,
      int subjectId) {
    log.debug(
        "updateLessonByLessonId id = {} lessonName = {} lessonStart = {} lessonEnd = {} audienceId = {} "
            + "subjectId = {}",
        id,
        lessonName,
        lessonStart,
        lessonEnd,
        audienceId,
        subjectId);
    lessonDao.update(id, new Lesson(id, lessonName, lessonStart, lessonEnd, audienceId, subjectId));
  }
}
