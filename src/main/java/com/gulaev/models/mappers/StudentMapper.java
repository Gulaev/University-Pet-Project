package com.gulaev.models.mappers;

import com.gulaev.models.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StudentMapper implements RowMapper<Student> {


  @Override
  public Student mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Student starting mapRow...");
    Student student = new Student();

    try {

      student.setStudentId(rs.getInt("student_id"));
      student.setFirstName(rs.getString("first_name"));
      student.setLastName(rs.getString("last_name"));
      student.setGroupId(rs.getInt("group_id"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", student);
    return student;
  }
}
