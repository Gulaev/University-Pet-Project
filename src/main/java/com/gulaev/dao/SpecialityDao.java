package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Speciality;
import com.gulaev.models.mappers.SpecialityMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SpecialityDao implements Dao<Speciality> {

  private JdbcTemplate jdbcTemplate;
  private SpecialityMapper specialityMapper;

  @Autowired
  public SpecialityDao(JdbcTemplate jdbcTemplate, SpecialityMapper specialityMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.specialityMapper = specialityMapper;
    log.trace("Initialization SpecialityDao");
  }

  @Override
  public Speciality getById(int id) {
    String sql1 = "SELECT * FROM specialities WHERE speciality_id = ?;";
    log.debug("getById id = {}", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, specialityMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Speciality with this Id does not Exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Speciality> loadAll() {
    String sql1 = "SELECT * FROM specialities;";
    log.debug("loadAll by speciality");
    return jdbcTemplate.query(sql1, specialityMapper);
  }

  @Override
  public void update(int id, Speciality speciality) {
    jdbcTemplate.update(
        "UPDATE specialities SET name_of_speciality=?, course=?, audience_id=? "
            + "WHERE speciality_id=?;",
        speciality.getNameOfSpeciality(),
        speciality.getCourse(),
        speciality.getAudienceId(),
        id);
    log.debug("update id={}", id);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM specialities WHERE speciality_id=?", id);
    log.debug("deleteById id={}", id);
  }

  public void addNewSpeciality(String nameOfSpecialities, int course, int audienceId) {
    jdbcTemplate.update(
        "INSERT INTO specialities(name_of_speciality, course, audience_id) VALUES (?, ?, ?)",
        nameOfSpecialities,
        course,
        audienceId);
    log.debug("addNewSpeciality");
  }
}
