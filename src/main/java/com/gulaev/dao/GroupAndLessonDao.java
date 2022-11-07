package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.GroupAndLesson;
import com.gulaev.models.mappers.GroupAndLessonMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GroupAndLessonDao implements Dao<GroupAndLesson> {

  private GroupAndLessonMapper groupAndLessonMapper;
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public GroupAndLessonDao(GroupAndLessonMapper groupAndLessonMapper, JdbcTemplate jdbcTemplate) {
    this.groupAndLessonMapper = groupAndLessonMapper;
    this.jdbcTemplate = jdbcTemplate;
    log.trace("Initialization GroupAndLessonDao");
  }

  @Override
  public GroupAndLesson getById(int id) {
    String sql = "SELECT * FROM group_and_lesson WHERE group_id=?";
    log.debug("getById id={}", id);
    try {
      return jdbcTemplate.query(sql, new Object[] {id}, groupAndLessonMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Group with this Id does not Exist id = {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<GroupAndLesson> loadAll() {
    String sql = "SELECT * FROM group_and_lesson";
    log.debug("loadAll by groupAndLesson");
    return jdbcTemplate.query(sql, groupAndLessonMapper);
  }

  @Override
  public void deleteById(int id) {
    log.debug("deleteById id = {}", id);
    jdbcTemplate.update("DELETE FROM group_and_lesson WHERE group_id=?", id);
  }

  @Override
  public void update(int id, GroupAndLesson groupAndLesson) {
    jdbcTemplate.update(
        "UPDATE group_and_lesson SET lesson_id=? WHERE group_id=?;",
        groupAndLesson.getLessonId(),
        id);
    log.debug("update groupAndLesson id = {}", id);
  }

  public void addNewGroupAndLesson(Integer groupId, Integer lessonId) {
    jdbcTemplate.update("INSERT INTO group_and_lesson VALUES (?, ?)", groupId, lessonId);
    log.debug("addNewGroupAndLesson in groupAndLesson");
  }
}
