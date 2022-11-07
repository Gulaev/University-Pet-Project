package com.gulaev.models.mappers;

import com.gulaev.models.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TeacherMapper implements RowMapper<Teacher> {

  @Override
  public Teacher mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Teacher starting mapRow...");
    Teacher teacher = new Teacher();
    try {

      teacher.setTeacherId(rs.getInt("teacher_id"));
      teacher.setFirstName(rs.getString("first_name"));
      teacher.setLastName(rs.getString("last_name"));

    } catch (SQLException e) {
      log.error("SQL Mapper Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", teacher);
    return teacher;
  }
}
