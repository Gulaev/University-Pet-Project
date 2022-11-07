package com.gulaev.models.mappers;

import com.gulaev.models.GroupAndLesson;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GroupAndLessonMapper implements RowMapper<GroupAndLesson> {


  @Override
  public GroupAndLesson mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping GroupAndLesson starting mapRow...");
    GroupAndLesson groupAndLesson = new GroupAndLesson();

    try {

      groupAndLesson.setLessonId(rs.getInt("lesson_id"));
      groupAndLesson.setGroupId(rs.getInt("group_id"));

    } catch (SQLException e) {

      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", groupAndLesson);
    return groupAndLesson;
  }
}
