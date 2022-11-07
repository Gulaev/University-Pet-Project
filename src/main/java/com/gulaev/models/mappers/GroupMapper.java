package com.gulaev.models.mappers;

import com.gulaev.dao.StudentDao;
import com.gulaev.models.Group;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class GroupMapper implements RowMapper<Group> {

  @Autowired private StudentDao studentDao;

  @Override
  public Group mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Group starting mapRow...");
    Group group = new Group();

    try {
      group.setGroupID(rs.getInt("group_id"));
      group.setCourse(rs.getInt("course"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }

    log.debug("Mapping ended {}", group);
    return group;
  }
}
