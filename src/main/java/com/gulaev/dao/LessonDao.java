package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Lesson;
import com.gulaev.models.mappers.LessonMapper;
import java.sql.Timestamp;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LessonDao implements Dao<Lesson> {

  private JdbcTemplate jdbcTemplate;
  private LessonMapper lessonMapper;

  @Autowired
  public LessonDao(JdbcTemplate jdbcTemplate, LessonMapper lessonMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.lessonMapper = lessonMapper;
    log.trace("Initialization LessonDao");
  }

  @Override
  public Lesson getById(int id) {
    String sql = "SELECT * FROM lessons WHERE lesson_id = ?;";
    log.debug("getById id = {};", id);
    try {
      return jdbcTemplate.query(sql, new Object[] {id}, lessonMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Lesson with this Id does not Exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Lesson> loadAll() {
    String sql1 = "SELECT * FROM lessons;";
    log.debug("loadAll by lessons");
    return jdbcTemplate.query(sql1, lessonMapper);
  }

  @Override
  public void deleteById(int id) {
    String sql = "DELETE FROM lessons WHERE lesson_id= ?";
    jdbcTemplate.update(sql, id);
    log.debug("deleteByID id= {}", id);
  }

  @Override
  public void update(int id, Lesson lesson) {
    jdbcTemplate.update(
        "UPDATE lessons SET lesson_name=?, lesson_start=?, lesson_end=?, audience_id=?, "
            + "subject_id=? WHERE lesson_id=?;",
        lesson.getLessonName(),
        lesson.getLessonStart(),
        lesson.getLessonEnd(),
        lesson.getAudienceId(),
        lesson.getSubjectId(),
        id);
    log.debug("update lesson id = {};", id);
  }

  public void addNewLesson(
      String lessonName,
      Timestamp lessonStart,
      Timestamp lessonEnd,
      int audienceId,
      int subjectId) {
    jdbcTemplate.update(
        "INSERT INTO lessons(lesson_name, lesson_start, lesson_end, audience_id, subject_id) "
            + "VALUES (?, ?, ?, ?, ?);",
        lessonName,
        lessonStart,
        lessonEnd,
        audienceId,
        subjectId);
    log.debug("addNewLesson in lessons");
  }
}
