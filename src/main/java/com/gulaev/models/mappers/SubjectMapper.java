package com.gulaev.models.mappers;

import com.gulaev.models.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SubjectMapper implements RowMapper<Subject> {


  @Override
  public Subject mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Subject starting mapRow...");
    Subject subject = new Subject();

    try {

      subject.setSubjectId(rs.getInt("subject_id"));
      subject.setSubjectName(rs.getString("subject_name"));
      subject.setSubjectDescription(rs.getString("subjects_description"));
      subject.setSpecialityId(rs.getInt("speciality_id"));
      subject.setSubjectTeacherId(rs.getInt("subject_teacher_id"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", subject);
    return subject;
  }
}
