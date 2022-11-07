package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Group;
import com.gulaev.models.mappers.GroupMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GroupDao implements Dao<Group> {

  private JdbcTemplate jdbcTemplate;
  private GroupMapper groupMapper;

  @Autowired
  public GroupDao(JdbcTemplate jdbcTemplate, GroupMapper groupMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.groupMapper = groupMapper;
    log.trace("Initialization GroupDao");
  }

  public Group getById(int id) {
    String sql1 = "SELECT * FROM groups WHERE group_id = ?;";
    log.debug("getById id = {};", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, groupMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Group with this Id does not Exist id = {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Group> loadAll() {
    String sql1 = "SELECT * FROM groups;";
    log.debug("loadAll by group");
    return jdbcTemplate.query(sql1, groupMapper);
  }

  @Override
  public void update(int id, Group group) {
    jdbcTemplate.update("UPDATE groups SET course=? WHERE group_id=?;", group.getCourse(), id);
    log.debug("update id = {}", id);
  }

  @Override
  public void deleteById(int id) {
    log.debug("deleteById id={}", id);
    jdbcTemplate.update("DELETE FROM groups WHERE group_id=?", id);
  }

  public void addNewGroup(int course) {
    jdbcTemplate.update("INSERT INTO groups(course) VALUES (?);", course);
    log.debug("addNewGroup courseId = {};", course);
  }
}
