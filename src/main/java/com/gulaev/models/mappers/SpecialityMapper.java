package com.gulaev.models.mappers;

import com.gulaev.models.Speciality;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SpecialityMapper implements RowMapper<Speciality> {

  @Override
  public Speciality mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Speciality starting mapRow...");
    Speciality speciality = new Speciality();

    try {

      speciality.setSpecialityId(rs.getInt("speciality_id"));
      speciality.setNameOfSpeciality(rs.getString("name_of_speciality"));
      speciality.setCourse(rs.getInt("course"));
      speciality.setAudienceId(rs.getInt("audience_id"));

    } catch (SQLException e) {
      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }
    log.debug("Mapping ended {}", speciality);
    return speciality;
  }
}
