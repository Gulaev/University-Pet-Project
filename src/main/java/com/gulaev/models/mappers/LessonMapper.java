package com.gulaev.models.mappers;

import com.gulaev.models.Lesson;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LessonMapper implements RowMapper<Lesson> {


  @Override
  public Lesson mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Lesson starting mapRow...");
    Lesson lesson = new Lesson();

    try {

      lesson.setLessonId(rs.getInt("lesson_id"));
      lesson.setLessonName(rs.getString("lesson_name"));
      lesson.setLessonStart(rs.getTimestamp("lesson_start"));
      lesson.setLessonEnd(rs.getTimestamp("lesson_end"));
      lesson.setAudienceId(rs.getInt("audience_id"));
      lesson.setSubjectId(rs.getInt("subject_id"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", lesson);
    return lesson;
  }
}
