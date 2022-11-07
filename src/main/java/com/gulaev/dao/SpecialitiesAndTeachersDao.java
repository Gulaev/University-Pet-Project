package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.SpecialitiesAndTeachers;
import com.gulaev.models.mappers.SpecialitiesAndTeachersMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SpecialitiesAndTeachersDao implements Dao<SpecialitiesAndTeachers> {

  private JdbcTemplate jdbcTemplate;
  private SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper;

  @Autowired
  public SpecialitiesAndTeachersDao(
      JdbcTemplate jdbcTemplate, SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.specialitiesAndTeachersMapper = specialitiesAndTeachersMapper;
    log.trace("Initialization SpecialitiesAndTeacherDao");
  }

  @Override
  public SpecialitiesAndTeachers getById(int id) {
    String sql = "SELECT * FROM specialities_and_teachers WHERE speciality_id=?";
    log.debug("getById id={}", id);
    try {
      return jdbcTemplate.query(sql, new Object[] {id}, specialitiesAndTeachersMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Speciality with this Id does not Exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<SpecialitiesAndTeachers> loadAll() {
    String sql = "SELECT * FROM specialities_and_teachers";
    log.debug("loadAll by specialitiesAndTeachers");
    return jdbcTemplate.query(sql, specialitiesAndTeachersMapper);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM specialities_and_teachers WHERE speciality_id=?", id);
    log.debug("deleteById id={}", id);
  }

  @Override
  public void update(int id, SpecialitiesAndTeachers specialitiesAndTeachers) {
    jdbcTemplate.update(
        "UPDATE specialities_and_teachers SET teacher_id=? WHERE speciality_id=?;",
        specialitiesAndTeachers.getTeacherId(),
        id);
    log.debug("UPDATE id={}", id);
  }

  public void addNewSpecialitiesAndTeachers(Integer specialitiesId, Integer teacherId) {
    jdbcTemplate.update(
        "INSERT INTO specialities_and_teachers VALUES (?, ?)", specialitiesId, teacherId);
    log.debug("addNewSpecialitiesAndTeachers");
  }
}
