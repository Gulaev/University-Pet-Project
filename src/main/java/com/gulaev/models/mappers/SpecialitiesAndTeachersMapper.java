package com.gulaev.models.mappers;

import com.gulaev.models.SpecialitiesAndTeachers;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SpecialitiesAndTeachersMapper implements RowMapper<SpecialitiesAndTeachers> {


  @Override
  public SpecialitiesAndTeachers mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping SpecialitiesAndTeachers starting mapRow...");
    SpecialitiesAndTeachers specialitiesAndTeachers = new SpecialitiesAndTeachers();

    try {

      specialitiesAndTeachers.setTeacherId(rs.getInt("teacher_id"));
      specialitiesAndTeachers.setSpecialityId(rs.getInt("speciality_id"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }

    log.debug("Mapping ended {}", specialitiesAndTeachers);
    return specialitiesAndTeachers;
  }
}
